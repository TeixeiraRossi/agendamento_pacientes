package org.example.entities;

import java.util.UUID;

public class Agendamento {
    private UUID id;
    private UUID pacienteId;
    private UUID medicoId;
    private String dataHora;

    public Agendamento(){
        this.id = UUID.randomUUID();
    }

    public Agendamento(UUID id, UUID pacienteId, UUID medicoId, String dataHora) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }

    public UUID getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(UUID medicoId) {
        this.medicoId = medicoId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
