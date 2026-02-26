package com.weg.biblioteca.dto.professor;

public record UpdateProfessorRequest(
        String nome,
        String email,
        String disciplina
) {
}
