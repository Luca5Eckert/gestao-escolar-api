package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.professor.ProfessorResponse;
import com.weg.biblioteca.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public ProfessorResponse toResponse(Professor professor){
        return new ProfessorResponse(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }

}
