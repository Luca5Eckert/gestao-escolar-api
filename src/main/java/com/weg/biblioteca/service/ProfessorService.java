package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.professor.ProfessorResponse;
import com.weg.biblioteca.dto.professor.CreateProfessorRequest;
import com.weg.biblioteca.dto.professor.UpdateProfessorRequest;
import com.weg.biblioteca.mapper.ProfessorMapper;
import com.weg.biblioteca.model.Professor;
import com.weg.biblioteca.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorResponse create(CreateProfessorRequest request){
        Professor professor = new Professor(
                request.nome(),
                request.email(),
                request.disciplina()
        );

        professorRepository.save(professor);
        
        return professorMapper.toResponse(professor);
    }

    public ProfessorResponse findById(long id){
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        
        return professorMapper.toResponse(professor);
    }

    public List<ProfessorResponse> findAll(){
        var professors = professorRepository.getAll();
        
        return professors.stream()
                .map(professorMapper::toResponse)
                .toList();
    }

    public ProfessorResponse update(long id, UpdateProfessorRequest request) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        professor.update(
                request.nome(),
                request.email()
        );

        professorRepository.update(professor);
        
        return professorMapper.toResponse(professor);
    }

    public void deletar(long id) {
        professorRepository.deleteById(id);
    }

}