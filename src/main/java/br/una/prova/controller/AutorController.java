package br.una.prova.controller;

import br.una.prova.entity.Autor;
import br.una.prova.repository.AutorRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/autor")
public class AutorController {

    private AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("autores", autorRepository.findAll(pageable));
        return "autor/listar";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("autor", autorRepository.findOne(id));
        return "autor/formulario";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Autor autor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "autor/formulario";
        }
        autorRepository.save(autor);
        return "redirect:/autor";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        autorRepository.delete(id);
        return "redirect:/autor";
    }

    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String texto) {

        model.addAttribute("autores", autorRepository.findByNomeLike("%" + texto + "%"));

        return "autor/listar";
    }
}