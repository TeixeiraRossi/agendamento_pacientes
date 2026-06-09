package org.example.entities;

import org.example.annotation.Obrigatorio;

import java.util.UUID;

public class Paciente {
    private UUID id;
    @Obrigatorio(mensagem = "O nome não pode estar vazio!")
    private String nome;
    private Integer sexo;
    private String dataNascimento;
    @Obrigatorio(mensagem = "O CPF é obrigatório no agendamento!")
    private String cpf;
    private String telefone;

    public Paciente(){
        this.id = UUID.randomUUID();
    }

    public Paciente(UUID id, String nome, Integer sexo, String dataNascimento, String cpf, String telefone){
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public Integer getSexo() {return sexo;}

    public void setSexo(Integer sexo) {this.sexo = sexo;}

    public String getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(String dataNascimento) {this.dataNascimento = dataNascimento;}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
