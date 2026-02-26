package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.turma.TurmaResponse;
import com.weg.biblioteca.dto.turma.CreateTurmaRequest;
import com.weg.biblioteca.dto.turma.UpdateTurmaRequest;
import com.weg.biblioteca.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaResponse create(
            @RequestBody CreateTurmaRequest request
    ) {
        return turmaService.create(request);
    }

    @GetMapping
    public List<TurmaResponse> findAll() {
        return turmaService.findAll();
    }

    @GetMapping("/{id}")
    public TurmaResponse findById(
            @PathVariable(value = "id") long id
    ) {
        return turmaService.findById(id);
    }

    @PutMapping("{id}")
    public TurmaResponse update(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateTurmaRequest updateTurmaRequest
    ) {
        return turmaService.update(id, updateTurmaRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        turmaService.deletar(id);
    }


}
