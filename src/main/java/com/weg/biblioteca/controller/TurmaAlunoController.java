package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.turma_aluno.TurmaAlunoResponse;
import com.weg.biblioteca.dto.turma_aluno.CreateTurmaAlunoRequest;
import com.weg.biblioteca.dto.turma_aluno.UpdateTurmaAlunoRequest;
import com.weg.biblioteca.service.TurmaAlunoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas/alunos")
public class TurmaAlunoController {

    private final TurmaAlunoService turmaAlunoService;

    public TurmaAlunoController(TurmaAlunoService turmaAlunoService) {
        this.turmaAlunoService = turmaAlunoService;
    }

    @PostMapping
    public TurmaAlunoResponse create(
            @RequestBody @Valid CreateTurmaAlunoRequest request
    ) {
        return turmaAlunoService.create(request);
    }

    @GetMapping
    public List<TurmaAlunoResponse> findAll() {
        return turmaAlunoService.findAll();
    }

    @GetMapping("/turma/{turmaId}/aluno/{alunoId}")
    public TurmaAlunoResponse findById(
            @PathVariable(value = "turmaId") long turmaId,
            @PathVariable(value = "alunoId") long alunoId
    ) {
        return turmaAlunoService.findById(turmaId, alunoId);
    }

    @PutMapping("/turma/{turmaId}/aluno/{alunoId}")
    public TurmaAlunoResponse update(
            @PathVariable(name = "turmaId") long turmaId,
            @PathVariable(name = "alunoId") long alunoId,
            @RequestBody UpdateTurmaAlunoRequest updateTurmaAlunoRequest
    ) {
        return turmaAlunoService.update(turmaId, alunoId, updateTurmaAlunoRequest);
    }

    @DeleteMapping("/{turmaId}/{alunoId}")
    public void delete(
            @PathVariable(name = "turmaId") long turmaId,
            @PathVariable(name = "alunoId") long alunoId
    ) {
        turmaAlunoService.deletar(turmaId, alunoId);
    }

}