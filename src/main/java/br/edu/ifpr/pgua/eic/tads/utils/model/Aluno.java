package br.edu.ifpr.pgua.eic.tads.utils.model;

public class Aluno {
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private String endereço;
    private String dataNascimento;

    public Aluno(String id, String nome, String cpf, String email, String senha, String telefone, String endereço, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereço = endereço;
        this.dataNascimento = dataNascimento;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereço() {
        return endereço;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    
}
