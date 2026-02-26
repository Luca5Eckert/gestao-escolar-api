package com.weg.biblioteca.dto.professor;

import java.time.LocalDate;

public record CreateProfessorRequest(
        String nome,
        String email,
        String disciplina
) {
}
