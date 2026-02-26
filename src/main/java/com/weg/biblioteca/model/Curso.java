package com.weg.biblioteca.model;

public class Curso {

    private Long id;
    private String nome;
    private String codigo;

    public Curso() {
    }

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public Curso(Long id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void update(String nome, String codigo) {
        if(nome != null) {
            this.nome = nome;
        }
        if(codigo != null) {
            this.codigo = codigo;
        }
    }
}
