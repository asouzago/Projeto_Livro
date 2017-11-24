package br.una.prova.controller;

import br.una.prova.entity.Editora;
import br.una.prova.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    EditoraRepository editoraRepository;

    @GetMapping
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("editoras", editoraRepository.findAll(pageable));
        return "/editora/listar";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("editora", editoraRepository.findOne(id));
        return "editora/formulario";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        editoraRepository.delete(id);
        return "redirect:/editora";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("editora", new Editora());
        return "editora/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Editora editora, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/editora/listar";
        }
        editoraRepository.save(editora);

        return "redirect:/editora";
    }

    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String texto) {

        model.addAttribute("editoras", editoraRepository.findByNomeLike("%" + texto + "%"));

        return "editora/listar";
    }
}
