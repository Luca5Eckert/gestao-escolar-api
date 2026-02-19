package com.weg.biblioteca.dto.usuario;

public record CreateUsuarioRequest(
        String nome,
        String email
) {
}
