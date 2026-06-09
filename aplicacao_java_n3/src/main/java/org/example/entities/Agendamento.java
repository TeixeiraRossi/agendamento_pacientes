package org.example.entities;

import org.example.annotation.Obrigatorio;

import java.util.UUID;

public class Agendamento {
    private UUID id;
    @Obrigatorio(mensagem = "O paciente é orbigatorio")
    private UUID pacienteId;

    @Obrigatorio(mensagem = "O medico é obrigatorio")
    private UUID medicoId;

    @Obrigatorio(mensagem = "A data é obrigatoria")
    private String data;

    @Obrigatorio(mensagem = "A hora é obrigatoria")
    private String hora;

    public Agendamento(){
        this.id = UUID.randomUUID();
    }

    public Agendamento(UUID id, UUID pacienteId, UUID medicoId, String data, String hora) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.data = data;
        this.hora = hora;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
