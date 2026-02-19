package com.weg.biblioteca.dto.livro;

public record UpdateLivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
