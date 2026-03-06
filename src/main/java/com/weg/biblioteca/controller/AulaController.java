package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.aula.AulaResponse;
import com.weg.biblioteca.dto.aula.CreateAulaRequest;
import com.weg.biblioteca.dto.aula.UpdateAulaRequest;
import com.weg.biblioteca.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public AulaResponse create(
            @RequestBody @Valid CreateAulaRequest request
    ) {
        return aulaService.create(request);
    }

    @GetMapping
    public List<AulaResponse> findAll() {
        return aulaService.findAll();
    }

    @GetMapping("/{id}")
    public AulaResponse findById(
            @PathVariable(value = "id") long id
    ) {
        return aulaService.findById(id);
    }

    @PutMapping("{id}")
    public AulaResponse update(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateAulaRequest updateAulaRequest
    ) {
        return aulaService.update(id, updateAulaRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") long id
    ) {
        aulaService.deletar(id);
    }


}
