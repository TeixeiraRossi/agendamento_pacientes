package org.example.services;

import org.example.annotation.Validador;
import org.example.dto.MedicoDto;
import org.example.entities.Medico;
import org.example.repositories.MedicoRepository;

import java.util.List;
import java.util.UUID;

public class MedicoService {
    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository){
        this.repository = repository;
    }

    public Medico salvar(MedicoDto dto){
        Medico medico = new Medico(UUID.randomUUID(), dto.getNome(), dto.getEspecialidade(), dto.getCpf());
        Validador.validar(medico);
        repository.salvar(medico);
        return medico;
    }

    public Medico atualizar(MedicoDto dto, String id){
        Medico medico = new Medico(UUID.fromString(id), dto.getNome(), dto.getEspecialidade(), dto.getCpf());
        Validador.validar(medico);
        return repository.atualizar(medico);
    }
    public List<Medico> buscarTodos(){
        return repository.buscarTodos();
    }
    public Medico buscarPorCpf(String cpf){
        return repository.buscarPorCpf(cpf);
    }
    public boolean excluir(String id){
        return repository.excluir(id);
    }
}
