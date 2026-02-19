package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.usuario.CreateUsuarioRequest;
import com.weg.biblioteca.dto.usuario.UpdateUsuarioRequest;
import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(CreateUsuarioRequest request){
        Usuario contato = new Usuario(
                request.nome(),
                request.email()
        );

        return usuarioRepository.save(contato);
    }

    public Usuario findById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.getAll();
    }

    public Usuario update(int id, UpdateUsuarioRequest request) {
        Usuario contato = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        contato.update(
                request.nome(),
                request.email()
        );

        return usuarioRepository.update(contato);
    }

    public void deletar(int id) {
        usuarioRepository.deleteById(id);
    }

}