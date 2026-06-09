package org.example.entities;

import org.example.annotation.Obrigatorio;

import java.util.UUID;

public class Usuario {
    private UUID id;

    @Obrigatorio(mensagem = "O nome é obrigatorio")
    private String nome;

    @Obrigatorio(mensagem = "O email é obrigatorio")
    private String email;

    @Obrigatorio(mensagem = "A senha é obrigatoria")
    private String senha;

    public Usuario(){
        this.id = UUID.randomUUID();
    }
    public Usuario(UUID id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
