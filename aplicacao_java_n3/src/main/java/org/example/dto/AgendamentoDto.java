package org.example.dto;

public class AgendamentoDto {
    private String pacienteId;
    private String medicoId;
    private String data;
    private String hora;

    public AgendamentoDto(String pacienteId, String medicoId, String data, String hora) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.data = data;
        this.hora = hora;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }
}
