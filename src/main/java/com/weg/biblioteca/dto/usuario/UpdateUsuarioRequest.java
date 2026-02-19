package com.weg.biblioteca.dto.usuario;

public record UpdateUsuarioRequest(
        String nome,
        String email
) {
}
