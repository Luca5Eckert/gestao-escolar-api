package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.aula.AulaResponse;
import com.weg.biblioteca.dto.aula.CreateAulaRequest;
import com.weg.biblioteca.dto.aula.UpdateAulaRequest;
import com.weg.biblioteca.mapper.AulaMapper;
import com.weg.biblioteca.model.Aula;
import com.weg.biblioteca.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;

    public AulaService(AulaRepository aulaRepository, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.aulaMapper = aulaMapper;
    }

    public AulaResponse create(CreateAulaRequest request){
        Aula aula = new Aula(
                request.turmaId(),
                request.datahora(),
                request.assunto()
        );

        aulaRepository.save(aula);

        return aulaMapper.toResponse(aula);
    }

    public AulaResponse findById(long id){
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula not found"));

        return aulaMapper.toResponse(aula);
    }

    public List<AulaResponse> findAll(){
        var aulas = aulaRepository.getAll();

        return aulas.stream()
                .map(aulaMapper::toResponse)
                .toList();
    }

    public AulaResponse update(long id, UpdateAulaRequest request) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        aula.update(
                request.assunto()
        );

        aulaRepository.update(aula);

        return aulaMapper.toResponse(aula);
    }

    public void deletar(long id) {
        aulaRepository.deleteById(id);
    }

}