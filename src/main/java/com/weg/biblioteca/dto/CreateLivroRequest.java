package com.weg.biblioteca.dto;

public record CreateLivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
