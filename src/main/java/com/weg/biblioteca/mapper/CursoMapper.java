package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.curso.CursoResponse;
import com.weg.biblioteca.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso){
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }
    
}
