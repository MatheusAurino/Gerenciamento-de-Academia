package br.edu.ifpr.pgua.eic.tads.utils.model;

public class Exercicio {
    private Integer id_exercicio;
    private String nome;
    private String grupo_muscular;

    public Exercicio(Integer id_exercicio, String nome, String grupo_muscular) {
        this.id_exercicio = id_exercicio;
        this.nome = nome;
        this.grupo_muscular = grupo_muscular;
    }

    public Integer getId_Exercicio() {
        return id_exercicio;
    }

    public String getNome() {
        return nome;
    }

    public String getGrupoMuscular() {
        return grupo_muscular;
    }

    public void setId_Exercicio(Integer id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGrupo_Muscular(String grupo_muscular) {
        this.grupo_muscular = grupo_muscular;
    }
}
