package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.turma.TurmaResponse;
import com.weg.biblioteca.dto.turma.CreateTurmaRequest;
import com.weg.biblioteca.dto.turma.UpdateTurmaRequest;
import com.weg.biblioteca.mapper.TurmaMapper;
import com.weg.biblioteca.model.Turma;
import com.weg.biblioteca.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;

    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
    }

    public TurmaResponse create(CreateTurmaRequest request){
        Turma turma = new Turma(
                request.nome(),
                request.cursoId(),
                request.professorId()
        );

        turmaRepository.save(turma);

        return turmaMapper.toResponse(turma);
    }

    public TurmaResponse findById(long id){
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma not found"));

        return turmaMapper.toResponse(turma);
    }

    public List<TurmaResponse> findAll(){
        var turmas = turmaRepository.getAll();

        return turmas.stream()
                .map(turmaMapper::toResponse)
                .toList();
    }

    public TurmaResponse update(long id, UpdateTurmaRequest request) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        turma.update(request.nome());

        turmaRepository.update(turma);

        return turmaMapper.toResponse(turma);
    }

    public void deletar(long id) {
        turmaRepository.deleteById(id);
    }

}