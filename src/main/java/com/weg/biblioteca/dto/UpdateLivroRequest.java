package com.weg.biblioteca.dto;

public record UpdateLivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
