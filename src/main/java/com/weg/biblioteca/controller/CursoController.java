package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.curso.CursoResponse;
import com.weg.biblioteca.dto.curso.CreateCursoRequest;
import com.weg.biblioteca.dto.curso.UpdateCursoRequest;
import com.weg.biblioteca.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public CursoResponse create(
            @RequestBody CreateCursoRequest request
    ) {
        return cursoService.create(request);
    }

    @GetMapping
    public List<CursoResponse> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public CursoResponse findById(
            @PathVariable(value = "id") long id
    ) {
        return cursoService.findById(id);
    }

    @PutMapping("{id}")
    public CursoResponse update(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateCursoRequest updateCursoRequest
    ) {
        return cursoService.update(id, updateCursoRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        cursoService.deletar(id);
    }


}
