package com.weg.biblioteca.dto.turma;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTurmaRequest(
        @NotBlank(message = "O nome é obrigatorio") String nome,
        @NotNull(message = "O id do curso é obrigatorio") Long cursoId,
        @NotNull(message = "O id do professor é obrigatorio") Long professorId
) {
}
