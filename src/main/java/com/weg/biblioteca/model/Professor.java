package com.weg.biblioteca.model;

public class Professor {

    private long id;

    private String nome;

    private String email;

    private String disciplina;

    public Professor() {
    }

    public Professor(String nome, String email, String disciplina) {
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
    }

    public Professor(long id, String nome, String email, String disciplina) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void update(String nome, String email) {
        if(nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
        if(email != null && !email.isEmpty()) {
            this.email = email;
        }

    }
}
