package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.curso.CursoResponse;
import com.weg.biblioteca.dto.curso.CreateCursoRequest;
import com.weg.biblioteca.dto.curso.UpdateCursoRequest;
import com.weg.biblioteca.dto.turma.TurmaResponse;
import com.weg.biblioteca.mapper.CursoMapper;
import com.weg.biblioteca.mapper.TurmaMapper;
import com.weg.biblioteca.model.Curso;
import com.weg.biblioteca.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final TurmaMapper turmaMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper, TurmaMapper turmaMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
        this.turmaMapper = turmaMapper;
    }

    public CursoResponse create(CreateCursoRequest request){
        Curso curso = new Curso(
                request.nome(),
                request.codigo()
        );

        cursoRepository.save(curso);

        return cursoMapper.toResponse(curso);
    }

    public CursoResponse findById(long id){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso not found"));

        return cursoMapper.toResponse(curso);
    }

    public List<CursoResponse> findAll(){
        var cursos = cursoRepository.getAll();

        return cursos.stream()
                .map(cursoMapper::toResponse)
                .toList();
    }

    public CursoResponse update(long id, UpdateCursoRequest request) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        curso.update(
                request.nome(),
                request.codigo()
        );

        cursoRepository.update(curso);

        return cursoMapper.toResponse(curso);
    }

    public void deletar(long id) {
        cursoRepository.deleteById(id);
    }

    public List<TurmaResponse> findTurmasByCursoId(long id) {
        var turmas = cursoRepository.findTurmasByCursoId(id);

        return turmas.stream()
                .map(turmaMapper::toResponse)
                .toList();
    }
}