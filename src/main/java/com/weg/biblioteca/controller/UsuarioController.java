package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.usuario.CreateUsuarioRequest;
import com.weg.biblioteca.dto.usuario.UpdateUsuarioRequest;
import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario create(
            @RequestBody CreateUsuarioRequest request
    ) {
        return usuarioService.create(request);
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getAmigoById(
            @PathVariable(value = "id") int id
    ) {
        return usuarioService.findById(id);
    }

    @PutMapping("{id}")
    public Usuario update(
            @PathVariable(name = "id") int id,
            @RequestBody UpdateUsuarioRequest updateUsuarioRequest
    ) {
        return usuarioService.update(id, updateUsuarioRequest);
    }

    @DeleteMapping("{id}")
    public void delete(
            @PathVariable(name = "id") int id
    ) {
        usuarioService.deletar(id);
    }


}
