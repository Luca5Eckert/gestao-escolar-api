package com.weg.biblioteca.dto.turma_aluno;

import jakarta.validation.constraints.NotNull;

public record CreateTurmaAlunoRequest(
        @NotNull(message = "O id da turma é obrigatorio") long idTurma,
        @NotNull(message = "O id do aluno é obrigatorio") long idAluno
) {
}
