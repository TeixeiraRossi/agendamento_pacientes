package org.example.repositories;

import org.example.entities.Paciente;

import java.util.List;

public interface PacienteRepository {

    void salvar(Paciente paciente);
    Paciente atualizar(Paciente paciente);
    Paciente buscarPorCpf(String id);
    List<Paciente> buscarTodos();
    boolean excluir(String id);
}
