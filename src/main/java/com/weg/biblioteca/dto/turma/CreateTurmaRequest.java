package com.weg.biblioteca.dto.turma;


public record CreateTurmaRequest(
        String nome,
        Long cursoId,
        Long professorId
) {
}
