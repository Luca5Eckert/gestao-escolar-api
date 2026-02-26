package com.weg.biblioteca.model;

import java.time.LocalDateTime;

public class Aula {

    private Long id;

    private Long turmaId;

    private LocalDateTime dataHora;

    private String assunto;

    public Aula() {
    }

    public Aula(Long turmaId, LocalDateTime dataHora, String assunto) {
        this.turmaId = turmaId;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Aula(Long id, Long turmaId, LocalDateTime dataHora, String assunto) {
        this.id = id;
        this.turmaId = turmaId;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void update(String assunto) {
        if(assunto != null && !assunto.isEmpty()) {
            this.assunto = assunto;
        }
    }
}

