package com.example.marit.trb2_dcc196;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marit on 18/11/2017.
 */

public class Livro implements Serializable {
    private List<Usuario> usuario = new ArrayList<Usuario>();
    private String titulo;
    private String editora;
    private int ano;
    private int id;

    public Livro(String titulo, String editora, int ano) {
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
    }

    public Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(ArrayList<Usuario> usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.titulo;
    }

}
