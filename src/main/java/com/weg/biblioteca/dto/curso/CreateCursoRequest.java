package com.weg.biblioteca.dto.curso;

import jakarta.validation.constraints.NotBlank;

public record CreateCursoRequest(
        @NotBlank(message = "O nome é obrigatoria") String nome,
        @NotBlank(message = "O codigo é obrigatorio") String codigo
) {
}
