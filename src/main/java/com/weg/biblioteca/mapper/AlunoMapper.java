package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.aluno.AlunoResponse;
import com.weg.biblioteca.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public AlunoResponse toResponse(Aluno aluno){
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getDataNascimento()
        );
    }

}
