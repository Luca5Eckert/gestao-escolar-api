package com.weg.biblioteca.dto.aluno;

import java.time.LocalDate;

public record CreateAlunoRequest(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
