package com.weg.biblioteca.model;

public class Nota {

    private Long id;

    private Long alunoId;

    private Long aulaId;

    private double valor;

    public Nota() {
    }

    public Nota(Long alunoId, Long aulaId, double valor) {
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
