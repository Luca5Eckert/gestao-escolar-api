package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.nota.NotaResponse;
import com.weg.biblioteca.dto.nota.CreateNotaRequest;
import com.weg.biblioteca.dto.nota.UpdateNotaRequest;
import com.weg.biblioteca.mapper.NotaMapper;
import com.weg.biblioteca.model.Nota;
import com.weg.biblioteca.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    public NotaService(NotaRepository notaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }

    public NotaResponse create(CreateNotaRequest request){
        if(request.valor() < 0 || request.valor() > 10){
            throw new RuntimeException("Valor da nota deve ser entre 0 e 10");
        }

        Nota nota = new Nota(
                request.alunoId(),
                request.aulaId(),
                request.valor()
        );

        notaRepository.save(nota);

        return notaMapper.toResponse(nota);
    }

    public NotaResponse findById(long id){
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota not found"));

        return notaMapper.toResponse(nota);
    }

    public List<NotaResponse> findAll(){
        var notas = notaRepository.getAll();

        return notas.stream()
                .map(notaMapper::toResponse)
                .toList();
    }

    public NotaResponse update(long id, UpdateNotaRequest request) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        nota.update(
                request.valor()
        );

        notaRepository.update(nota);

        return notaMapper.toResponse(nota);
    }

    public void deletar(long id) {
        notaRepository.deleteById(id);
    }

}