package com.weg.biblioteca.model;

import java.time.LocalDate;

public class Livro {

    private int id;

    private String titulo;

    private String autor;

    private int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public Livro(int id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void update(String titulo, String autor, int anoPublicacao) {
        if(titulo != null){
            this.titulo = titulo;
        }
        if(autor != null){
            this.autor = autor;
        }
        if(anoPublicacao != 0 && anoPublicacao <= LocalDate.now().getYear()){
            this.anoPublicacao = anoPublicacao;
        }
    }

}
