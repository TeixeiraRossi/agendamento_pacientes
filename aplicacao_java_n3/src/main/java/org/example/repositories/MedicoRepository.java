package org.example.repositories;

import org.example.entities.Medico;

import java.util.List;

public interface MedicoRepository {
    void salvar(Medico medico);
    Medico atualizar(Medico medico);
    Medico buscarPorCpf(String id);
    List<Medico> buscarTodos();
    Medico excluir(String id);
}
