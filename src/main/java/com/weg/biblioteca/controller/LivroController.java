package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.CreateLivroRequest;
import com.weg.biblioteca.dto.UpdateLivroRequest;
import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public Livro create(
            @RequestBody CreateLivroRequest request
    ) {
        return livroService.create(request);
    }

    @GetMapping
    public List<Livro> getLivros() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public Livro getAmigoById(
            @PathVariable(value = "id") int id
    ) {
        return livroService.findById(id);
    }

    @PutMapping("{id}")
    public Livro update(
            @PathVariable(name = "id") int id,
            @RequestBody UpdateLivroRequest updateLivroRequest
    ) {
        return livroService.update(id, updateLivroRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") int id
    ) {
        livroService.deletar(id);
    }


}
