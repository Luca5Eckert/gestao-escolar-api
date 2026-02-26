package com.weg.biblioteca.model;

public class Turma {

    private Long id;

    private String nome;

    private Long cursoId;

    private Long professorId;

    public Turma() {
    }

    public Turma(String nome, Long cursoId, Long professorId) {
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
    }

    public Turma(Long id, String nome, Long cursoId, Long professorId) {
        this.id = id;
        this.nome = nome;
        this.cursoId = cursoId;
        this.professorId = professorId;
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

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public void update(String nome) {
        if(nome != null) {
            this.nome = nome;
        }
    }
}
