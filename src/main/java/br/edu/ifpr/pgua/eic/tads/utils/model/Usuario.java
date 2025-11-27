package br.edu.ifpr.pgua.eic.tads.utils.model;

public class Usuario {

    private String id_usuario;
    private String nome;
    private String cpf;
    private String senha;
    private String tipo;

    public Usuario(String id_usuario, String nome, String cpf, String senha, String tipo) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
