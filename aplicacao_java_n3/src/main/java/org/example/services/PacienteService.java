package org.example.services;

import org.example.annotation.Validador;
import org.example.dto.PacienteDto;
import org.example.entities.Paciente;
import org.example.repositories.PacienteRepository;

public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente salvar(PacienteDto dto) {
        Paciente paciente = new Paciente(
                java.util.UUID.randomUUID(),
                dto.getNome(),
                dto.getSexo(),
                dto.getCpf(),
                dto.getDataNascimento(),
                dto.getTelefone());

        Validador.validar(paciente);

        repository.salvar(paciente);

        return paciente;
    }
}
