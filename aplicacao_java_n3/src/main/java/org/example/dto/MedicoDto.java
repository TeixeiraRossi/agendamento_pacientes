package org.example.dto;

public class MedicoDto {
    private String nome;
    private String especialidade;
    private String cpf;

    public MedicoDto(String nome, String especialidade, String cpf){
        this.nome = nome;
        this.especialidade = especialidade;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getCpf() {
        return cpf;
    }
}
