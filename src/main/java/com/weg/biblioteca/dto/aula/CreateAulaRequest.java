package com.weg.biblioteca.dto.aula;

import java.time.LocalDateTime;

public record CreateAulaRequest(
        long turmaId,
        LocalDateTime datahora,
        String assunto
) {
}
