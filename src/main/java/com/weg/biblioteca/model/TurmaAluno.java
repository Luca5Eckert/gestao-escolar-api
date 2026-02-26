package com.weg.biblioteca.model;

public class TurmaAluno {

    private long turmaId;
    private long alunoId;

    public TurmaAluno() {
    }

    public TurmaAluno(long turmaId, long alunoId) {
        this.turmaId = turmaId;
        this.alunoId = alunoId;
    }

    public long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(long turmaId) {
        this.turmaId = turmaId;
    }

    public long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(long alunoId) {
        this.alunoId = alunoId;
    }

    public void update(long turmaId) {
        this.turmaId = turmaId;
    }
}

