package com.weg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record UpdateEmprestimoRequest(
        LocalDate dataEmprestimo
) {
}
