package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.turma.TurmaResponse;
import com.weg.biblioteca.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public TurmaResponse toResponse(Turma turma){
        return new TurmaResponse(
                turma.getId(),
                turma.getNome(),
                turma.getCursoId(),
                turma.getProfessorId()
        );
    }

}
