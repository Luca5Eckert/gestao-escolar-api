package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.aluno.CreateAlunoRequest;
import com.weg.biblioteca.dto.aluno.UpdateAlunoRequest;
import com.weg.biblioteca.dto.aluno.AlunoResponse;
import com.weg.biblioteca.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoResponse create(
            @RequestBody CreateAlunoRequest request
    ) {
        return alunoService.create(request);
    }

    @GetMapping
    public List<AlunoResponse> findAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public AlunoResponse findById(
            @PathVariable(value = "id") long id
    ) {
        return alunoService.findById(id);
    }

    @PutMapping("{id}")
    public AlunoResponse update(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateAlunoRequest updateAlunoRequest
    ) {
        return alunoService.update(id, updateAlunoRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        alunoService.deletar(id);
    }


}
