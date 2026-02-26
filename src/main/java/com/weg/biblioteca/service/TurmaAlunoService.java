package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.turma_aluno.TurmaAlunoResponse;
import com.weg.biblioteca.dto.turma_aluno.CreateTurmaAlunoRequest;
import com.weg.biblioteca.dto.turma_aluno.UpdateTurmaAlunoRequest;
import com.weg.biblioteca.mapper.TurmaAlunoMapper;
import com.weg.biblioteca.model.TurmaAluno;
import com.weg.biblioteca.repository.TurmaAlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaAlunoService {

    private final TurmaAlunoRepository turmaAlunoRepository;
    private final TurmaAlunoMapper turmaAlunoMapper;

    public TurmaAlunoService(TurmaAlunoRepository turmaAlunoRepository, TurmaAlunoMapper turmaAlunoMapper) {
        this.turmaAlunoRepository = turmaAlunoRepository;
        this.turmaAlunoMapper = turmaAlunoMapper;
    }

    public TurmaAlunoResponse create(CreateTurmaAlunoRequest request){
        TurmaAluno turmaAluno = new TurmaAluno(
                request.idTurma(),
                request.idAluno()
        );

        turmaAlunoRepository.save(turmaAluno);

        return turmaAlunoMapper.toResponse(turmaAluno);
    }

    public TurmaAlunoResponse findById(long turmaId, long alunoId){
        TurmaAluno turmaAluno = turmaAlunoRepository.findByTurmaIdAndAlunoId(turmaId, alunoId)
                .orElseThrow(() -> new RuntimeException("TurmaAluno not found"));

        return turmaAlunoMapper.toResponse(turmaAluno);
    }

    public List<TurmaAlunoResponse> findAll(){
        var turmaAlunos = turmaAlunoRepository.getAll();

        return turmaAlunos.stream()
                .map(turmaAlunoMapper::toResponse)
                .toList();
    }

    public TurmaAlunoResponse update(long turmaId, long alunoId, UpdateTurmaAlunoRequest request) {
        TurmaAluno turmaAluno = turmaAlunoRepository.findByTurmaIdAndAlunoId(turmaId, alunoId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        turmaAluno.update(
                request.idTurma()
        );

        turmaAlunoRepository.update(turmaId, alunoId, turmaAluno);

        return turmaAlunoMapper.toResponse(turmaAluno);
    }

    public void deletar(long turmaId, long alunoId) {
        turmaAlunoRepository.delete(turmaId, alunoId);
    }

}