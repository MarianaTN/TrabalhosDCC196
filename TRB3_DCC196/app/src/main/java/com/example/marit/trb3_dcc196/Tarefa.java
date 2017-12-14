package com.example.marit.trb3_dcc196;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by marit on 03/12/2017.
 */

public class Tarefa implements Serializable {
    private int id;
    private String titulo;
    private String descricao;
    private Integer grauDificuldade;
    private String estado;
    private ArrayList<Tag> tags = new ArrayList<>();

    public Tarefa(String titulo, String descricao, Integer grauDificuldade,String estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.grauDificuldade = grauDificuldade;
        this.estado = estado;
    }

    public Tarefa() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getGrauDificuldade() {
        return grauDificuldade;
    }

    public void setGrauDificuldade(Integer grauDificuldade) {
        this.grauDificuldade = grauDificuldade;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void insereTag(Tag t) {tags.add(t);}
    @Override
    public String toString() {
        return getTitulo();
    }

}
