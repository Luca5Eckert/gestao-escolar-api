package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.emprestimo.CreateEmprestimoRequest;
import com.weg.biblioteca.dto.emprestimo.UpdateEmprestimoRequest;
import com.weg.biblioteca.model.Emprestimo;
import com.weg.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService usuarioService;

    public EmprestimoController(EmprestimoService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Emprestimo create(
            @RequestBody CreateEmprestimoRequest request
    ) {
        return usuarioService.create(request);
    }

    @GetMapping
    public List<Emprestimo> getEmprestimos() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Emprestimo getAmigoById(
            @PathVariable(value = "id") int id
    ) {
        return usuarioService.findById(id);
    }

    @PutMapping("{id}")
    public Emprestimo update(
            @PathVariable(name = "id") int id,
            @RequestBody UpdateEmprestimoRequest updateEmprestimoRequest
    ) {
        return usuarioService.update(id, updateEmprestimoRequest);
    }

    @PostMapping("{id}/devolucao")
    public Emprestimo devolver(
            @PathVariable(name = "id") int id
    ) {
        return usuarioService.devolver(id);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") int id
    ) {
        usuarioService.deleteById(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Emprestimo> getEmprestimosByUsuarioId(
            @PathVariable(name = "usuarioId") int usuarioId
    ) {
        return usuarioService.findByUsuarioId(usuarioId);
    }

}
