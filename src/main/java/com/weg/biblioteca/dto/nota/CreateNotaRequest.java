package com.weg.biblioteca.dto.nota;

public record CreateNotaRequest(
        long alunoId,
        long aulaId,
        double valor
) {
}
