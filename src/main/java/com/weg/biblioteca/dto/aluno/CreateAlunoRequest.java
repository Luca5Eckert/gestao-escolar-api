package com.weg.biblioteca.dto.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateAlunoRequest(
        @NotBlank(message = "O nome é obrigatorio") String nome,
        @NotBlank(message = "O email é obrigatorio") String email,
        @NotBlank(message = "A matricula é obrigatoria") String matricula,
        @NotNull(message = "A data nascimento é obrigatoria") LocalDate dataNascimento
) {
}
