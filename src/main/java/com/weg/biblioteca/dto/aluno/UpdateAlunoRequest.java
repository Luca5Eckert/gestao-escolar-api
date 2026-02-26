package com.weg.biblioteca.dto.aluno;

public record UpdateAlunoRequest(
        String nome,
        String email
) {
}
