package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.emprestimo.CreateEmprestimoRequest;
import com.weg.biblioteca.dto.emprestimo.UpdateEmprestimoRequest;
import com.weg.biblioteca.model.Emprestimo;
import com.weg.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo create(CreateEmprestimoRequest request){
        if(emprestimoRepository.existsByLivroIdAndDataDevolucaoIsNull(request.livroId())){
            throw new RuntimeException("Livro já emprestado");
        }

        Emprestimo emprestimo = new Emprestimo(
                request.livroId(),
                request.usuarioId(),
                request.dataEmprestimo()
        );

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo findById(int id){
        return emprestimoRepository.findById(id).orElse(null);
    }

    public List<Emprestimo> findAll(){
        return emprestimoRepository.getAll();
    }

    public Emprestimo update(int id, UpdateEmprestimoRequest request) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo not found"));

        emprestimo.update(
                request.dataEmprestimo()
        );

        return emprestimoRepository.update(emprestimo);
    }

    public void deleteById(int id) {
        emprestimoRepository.deleteById(id);
    }

    public Emprestimo devolver(int id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo not found"));

        emprestimo.setDataDevolucao(LocalDate.now());

        return emprestimoRepository.update(emprestimo);
    }

    public List<Emprestimo> findByUsuarioId(int usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId);
    }
}