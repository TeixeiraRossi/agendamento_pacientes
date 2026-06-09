package org.example.repositories;

import org.example.entities.Agendamento;

import java.util.List;

public interface AgendamentoRepository {
    void salvar(Agendamento agendamento);

    Agendamento atualizar(Agendamento agendamento);

    Agendamento buscarPorId(String id);

    List<Agendamento> buscarTodos();

    boolean excluir(String id);
}
