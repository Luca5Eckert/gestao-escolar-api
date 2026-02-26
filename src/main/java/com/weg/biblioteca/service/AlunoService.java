package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.aluno.AlunoResponse;
import com.weg.biblioteca.dto.aluno.CreateAlunoRequest;
import com.weg.biblioteca.dto.aluno.UpdateAlunoRequest;
import com.weg.biblioteca.mapper.AlunoMapper;
import com.weg.biblioteca.model.Aluno;
import com.weg.biblioteca.repository.AlunoRepository;
import com.weg.biblioteca.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    public AlunoResponse create(CreateAlunoRequest request){
        Aluno aluno = new Aluno(
                request.nome(),
                request.email(),
                request.matricula(),
                request.dataNascimento()
        );

        if(alunoRepository.existsByEmail(aluno.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        alunoRepository.save(aluno);

        return alunoMapper.toResponse(aluno);
    }

    public AlunoResponse findById(long id){
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno not found"));

        return alunoMapper.toResponse(aluno);
    }

    public List<AlunoResponse> findAll(){
        var alunos = alunoRepository.getAll();

        return alunos.stream()
                .map(alunoMapper::toResponse)
                .toList();
    }

    public AlunoResponse update(long id, UpdateAlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        aluno.update(
                request.nome(),
                request.email()
        );

        alunoRepository.update(aluno);

        return alunoMapper.toResponse(aluno);
    }

    public void deletar(long id) {
        alunoRepository.deleteById(id);
    }

    public List<Double> getNotasByAlunoId(long id) {
        return alunoRepository.findNotasByAlunoId(id);
    }
}