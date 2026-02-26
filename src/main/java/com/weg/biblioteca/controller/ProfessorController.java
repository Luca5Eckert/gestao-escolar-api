package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.professor.ProfessorResponse;
import com.weg.biblioteca.dto.professor.CreateProfessorRequest;
import com.weg.biblioteca.dto.professor.UpdateProfessorRequest;
import com.weg.biblioteca.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorResponse create(
            @RequestBody CreateProfessorRequest request
    ) {
        return professorService.create(request);
    }

    @GetMapping
    public List<ProfessorResponse> findAll() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public ProfessorResponse findById(
            @PathVariable(value = "id") int id
    ) {
        return professorService.findById(id);
    }

    @PutMapping("{id}")
    public ProfessorResponse update(
            @PathVariable(name = "id") int id,
            @RequestBody UpdateProfessorRequest updateProfessorRequest
    ) {
        return professorService.update(id, updateProfessorRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") int id
    ) {
        professorService.deletar(id);
    }


}
