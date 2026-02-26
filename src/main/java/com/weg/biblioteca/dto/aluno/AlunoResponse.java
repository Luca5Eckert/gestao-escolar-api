package com.weg.biblioteca.dto.aluno;

import java.time.LocalDate;

public record AlunoResponse(
        long id,
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
