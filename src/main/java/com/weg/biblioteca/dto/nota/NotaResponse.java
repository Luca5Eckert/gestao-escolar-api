package com.weg.biblioteca.dto.nota;

public record NotaResponse(
        long id,
        long alunoId,
        long aulaId,
        double valor
) {
}
