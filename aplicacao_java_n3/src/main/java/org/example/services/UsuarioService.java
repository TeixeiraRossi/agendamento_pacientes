package org.example.services;

import org.example.annotation.Validador;
import org.example.dto.UsuarioDto;
import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;

import java.util.List;
import java.util.UUID;

public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario salvar(UsuarioDto dto){
        Usuario usuario = new Usuario(UUID.randomUUID(), dto.getNome(), dto.getEmail(), dto.getSenha());

        Validador.validar(usuario);
        repository.salvar(usuario);
        return usuario;
    }
    public Usuario atualizar(UsuarioDto dto, String id){
        Usuario usuario = new Usuario(UUID.fromString(id), dto.getNome(), dto.getEmail(), dto.getSenha());
        Validador.validar(usuario);
        return repository.atualizar(usuario);

    }

    public List<Usuario>buscarTodos(){
        return repository.buscarTodos();
    }
    public Usuario buscarPorEmail(String email){
        return repository.buscarPorEmail(email);
    }
    public boolean excluir(String id){
        return repository.excluir(id);
    }
}
