package com.weg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CreateEmprestimoRequest(
        int livroId,
        int usuarioId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
