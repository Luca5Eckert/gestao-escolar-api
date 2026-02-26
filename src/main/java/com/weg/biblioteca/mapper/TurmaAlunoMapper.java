package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.turma_aluno.TurmaAlunoResponse;
import com.weg.biblioteca.model.TurmaAluno;
import org.springframework.stereotype.Component;

@Component
public class TurmaAlunoMapper {

    public TurmaAlunoResponse toResponse(TurmaAluno turmaAluno){
        return new TurmaAlunoResponse(
                turmaAluno.getTurmaId(),
                turmaAluno.getAlunoId()
        );
    }

}
