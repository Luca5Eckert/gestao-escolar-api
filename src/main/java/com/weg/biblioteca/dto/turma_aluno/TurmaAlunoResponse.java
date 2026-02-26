package com.weg.biblioteca.dto.turma_aluno;

import java.time.LocalDate;

public record TurmaAlunoResponse(
        long idTurma,
        long idAluno
) {
}
