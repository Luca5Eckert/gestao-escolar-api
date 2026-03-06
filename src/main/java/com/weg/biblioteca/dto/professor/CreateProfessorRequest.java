package com.weg.biblioteca.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateProfessorRequest(
        @NotBlank(message = "O nome precisa ser obrigatorio") String nome,
        @Email(message = "Email invalido") @NotBlank(message = "O email é obrigatorio") String email,
        @NotBlank(message = "A disciplina é obrigatorio") String disciplina
) {
}
