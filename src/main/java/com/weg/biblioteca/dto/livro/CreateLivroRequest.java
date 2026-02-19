package com.weg.biblioteca.dto.livro;

public record CreateLivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
