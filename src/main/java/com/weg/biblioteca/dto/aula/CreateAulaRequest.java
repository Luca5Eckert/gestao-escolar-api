package com.weg.biblioteca.dto.aula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAulaRequest(
        @NotNull(message = "A turma id é obrigatorio") Long turmaId,
        @NotNull(message = "A data hora é obrigatoria") LocalDateTime datahora,
        @NotBlank(message = "O assunto é obrigatorio") String assunto
) {
}
