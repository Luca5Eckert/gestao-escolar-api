package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.nota.NotaResponse;
import com.weg.biblioteca.dto.nota.CreateNotaRequest;
import com.weg.biblioteca.dto.nota.UpdateNotaRequest;
import com.weg.biblioteca.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaResponse create(
            @RequestBody @Valid CreateNotaRequest request
    ) {
        return notaService.create(request);
    }

    @GetMapping
    public List<NotaResponse> findAll() {
        return notaService.findAll();
    }

    @GetMapping("/{id}")
    public NotaResponse findById(
            @PathVariable(value = "id") long id
    ) {
        return notaService.findById(id);
    }

    @PutMapping("{id}")
    public NotaResponse update(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateNotaRequest updateNotaRequest
    ) {
        return notaService.update(id, updateNotaRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        notaService.deletar(id);
    }


}
