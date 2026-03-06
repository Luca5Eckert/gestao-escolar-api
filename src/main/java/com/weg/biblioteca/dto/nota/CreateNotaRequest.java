package com.weg.biblioteca.dto.nota;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateNotaRequest(
        @NotNull(message = "A aluna id é obrigatorio") long alunoId,
        @NotNull(message = "A aula id é obrigatorio") long aulaId,
        @PositiveOrZero(message = "A nota precisa ser maior ou igual a 0") double valor
) {
}
