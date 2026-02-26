package com.weg.biblioteca.dto.professor;

public record ProfessorResponse(
        long id,
        String nome,
        String email,
        String disciplina
) {
}
