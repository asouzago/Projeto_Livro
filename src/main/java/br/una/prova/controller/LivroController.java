package br.una.prova.controller;

import br.una.prova.entity.Livro;
import br.una.prova.entity.Voto;
import br.una.prova.repository.AutorRepository;
import br.una.prova.repository.EditoraRepository;
import br.una.prova.repository.LivroRepository;
import br.una.prova.repository.VotoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/livro")
public class LivroController {

    private LivroRepository livroRepository;
    private EditoraRepository editoraRepository;
    private AutorRepository autorRepository;
    private VotoRepository votoRepository;

    public LivroController(EditoraRepository editoraRepository, LivroRepository livroRepository, AutorRepository autorRepository, VotoRepository votoRepository) {
        this.livroRepository = livroRepository;
        this.editoraRepository = editoraRepository;
        this.autorRepository = autorRepository;
        this.votoRepository = votoRepository;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 15) Pageable pageable) {
        model.addAttribute("livros", livroRepository.findAll(pageable));
        return "livro/listar";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("livro", livroRepository.findOne(id));
        model.addAttribute("diretores", editoraRepository.findAll());
        model.addAttribute("atores", autorRepository.findAll());
        return "livro/formulario";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        // Quando o livro já possui votação precisa excluir os votos para depois excluir o livro
        for (Voto voto : votoRepository.findByLivroLike(livroRepository.findOne(id))) {
            votoRepository.delete(voto.getId());
        }

        livroRepository.delete(id);
        return "redirect:/livro";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("editoras", editoraRepository.findAll());
        model.addAttribute("autores", autorRepository.findAll());
        return "livro/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Livro livro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "livro/formulario";
        }

        livroRepository.save(livro);
        return "redirect:/livro";
    }

    @GetMapping("/votar/{id}")
    public String redirecionar(Model model, @PathVariable Integer id) {
        model.addAttribute("voto", new Voto());
        model.addAttribute("livros", livroRepository.findOne(id));
        return "voto/formulario";
    }

    @GetMapping("/relatorio")
    public String relatorio(Model model, @RequestParam(defaultValue = "pdf") String format, HttpServletResponse response) {
        model.addAttribute("datasource", livroRepository.findAll());
        model.addAttribute("format", format);
        return "reports/livros";
    }
}
