package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.nota.NotaResponse;
import com.weg.biblioteca.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public NotaResponse toResponse(Nota nota){
        return new NotaResponse(
                nota.getId(),
                nota.getAlunoId(),
                nota.getAulaId(),
                nota.getValor()
        );
    }

}
