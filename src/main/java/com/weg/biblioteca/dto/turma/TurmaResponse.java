package com.weg.biblioteca.dto.turma;

import java.time.LocalDate;

public record TurmaResponse(
        long id,
        String nome,
        Long cursoId,
        Long professorId
) {
}
