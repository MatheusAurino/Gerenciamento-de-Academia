package br.edu.ifpr.pgua.eic.tads.utils.model;

public class Plano {

    private Integer id_plano;
    private String nome;
    private Double valor;
    private String beneficios;

    public Plano(Integer id_plano, String nome, Double valor, String beneficios) {
        this.id_plano = id_plano;
        this.nome = nome;
        this.valor = valor;
        this.beneficios = beneficios;
    }

    public Plano(String nome, Double valor, String beneficios) {
        this.nome = nome;
        this.valor = valor;
        this.beneficios = beneficios;
    }

    public Integer getId_plano() {
        return id_plano;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setId_plano(Integer id_plano) {
        this.id_plano = id_plano;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }
}