package org.example.repositories;

import org.example.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    void salvar(Usuario usuario);
    Usuario atualizar(Usuario usuario);
    Usuario buscarPorEmail(String email);
    List<Usuario>buscarTodos();
    boolean excluir(String id);
}
