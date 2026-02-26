package com.weg.biblioteca.dto.aula;

import java.time.LocalDateTime;

public record AulaResponse(
        long id,
        long turmaId,
        LocalDateTime datahora,
        String assunto
) {
}
