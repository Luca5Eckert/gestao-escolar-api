package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.aula.AulaResponse;
import com.weg.biblioteca.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public AulaResponse toResponse(Aula aluno){
        return new AulaResponse(
                aluno.getId(),
                aluno.getTurmaId(),
                aluno.getDataHora(),
                aluno.getAssunto()
        );
    }

}
