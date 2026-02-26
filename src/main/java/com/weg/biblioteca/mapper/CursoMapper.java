package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.curso.CursoResponse;
import com.weg.biblioteca.model.Curso;

public class CursoMapper {

    public CursoResponse toResponse(Curso curso){
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }
    
}
