package org.example.services;

import org.example.annotation.Validador;
import org.example.dto.AgendamentoDto;
import org.example.entities.Agendamento;
import org.example.repositories.AgendamentoRepository;

import java.util.List;
import java.util.UUID;

public class AgendamentoService {
    private final AgendamentoRepository repository;
    public AgendamentoService(AgendamentoRepository repository){
        this.repository = repository;
    }
    public Agendamento salvar(AgendamentoDto dto){
        Agendamento agendamento = new Agendamento(UUID.randomUUID(), UUID.fromString(dto.getPacienteId()), UUID.fromString(dto.getMedicoId()), dto.getData(), dto.getHora());

        Validador.validar(agendamento);
        repository.salvar(agendamento);
        return agendamento;
    }

    public Agendamento atualizar(AgendamentoDto dto, String id) {

        Agendamento agendamento = new Agendamento(
                UUID.fromString(id),
                UUID.fromString(dto.getPacienteId()),
                UUID.fromString(dto.getMedicoId()),
                dto.getData(),
                dto.getHora()
        );

        Validador.validar(agendamento);

        return repository.atualizar(agendamento);
    }

    public List<Agendamento> buscarTodos() {
        return repository.buscarTodos();
    }

    public Agendamento buscarPorId(String id) {
        return repository.buscarPorId(id);
    }

    public boolean excluir(String id) {
        return repository.excluir(id);
    }
}
