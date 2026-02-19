package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.livro.CreateLivroRequest;
import com.weg.biblioteca.dto.livro.UpdateLivroRequest;
import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.repository.EmprestimoRepository;
import com.weg.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public LivroService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Livro create(CreateLivroRequest request){
        Livro contato = new Livro(
                request.titulo(),
                request.autor(),
                request.anoPublicacao()
        );

        return livroRepository.save(contato);
    }

    public Livro findById(int id){
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> findAll(){
        return livroRepository.getAll();
    }

    public Livro update(int id, UpdateLivroRequest request) {
        Livro contato = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        contato.update(
                request.titulo(),
                request.autor(),
                request.anoPublicacao()
        );

        return livroRepository.update(contato);
    }

    public void deletar(int id) {
        if(emprestimoRepository.existsByLivroIdAndDataDevolucaoIsNull(id)){
            throw new RuntimeException("Livro está emprestado e não pode ser deletado");
        }
        livroRepository.deleteById(id);
    }

}