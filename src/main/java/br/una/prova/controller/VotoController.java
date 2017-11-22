package br.una.prova.controller;

import br.una.prova.entity.Voto;
import br.una.prova.repository.LivroRepository;
import br.una.prova.repository.VotoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/voto")
public class VotoController {

    private LivroRepository livroRepository;
    private VotoRepository votoRepository;

    public VotoController(LivroRepository livroRepository, VotoRepository votoRepository) {
        this.livroRepository = livroRepository;
        this.votoRepository = votoRepository;
    }

    @GetMapping
    public String novo(Model model) {
        model.addAttribute("voto", new Voto());
        model.addAttribute("filmes", livroRepository.findAll());
        return "voto/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Voto voto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "voto/formulario";
        }

        model.addAttribute("filmes", livroRepository.findAll());
        votoRepository.save(voto);
        return "redirect:/livro";
    }
}
