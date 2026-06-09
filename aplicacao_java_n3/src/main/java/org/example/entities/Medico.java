package org.example.entities;

import org.example.annotation.Obrigatorio;

import java.util.UUID;

public class Medico {
    private UUID id;
    @Obrigatorio(mensagem = "O nome é obrigatorio")
    private String nome;

    @Obrigatorio(mensagem = "A especialidade é obrigatória")
    private String especialidade;

    @Obrigatorio(mensagem = "O cfp é obrigatorio")
    private String cpf;

    public Medico(){
        this.id = UUID.randomUUID();
    }

    public Medico(UUID id, String nome, String especialidade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
