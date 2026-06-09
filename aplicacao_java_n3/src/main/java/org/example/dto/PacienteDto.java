package org.example.dto;

public class PacienteDto {
    String nome;
    Integer sexo;
    String cpf;
    String dataNascimento;
    String telefone;

    public PacienteDto(String nome, Integer sexo, String cpf, String dataNascimento, String telefone) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public String getNome() {return nome;}

    public Integer getSexo() {return sexo;}

    public String getCpf() {return cpf;}

    public String getDataNascimento() {return dataNascimento;}

    public String getTelefone() {return telefone;}
}
