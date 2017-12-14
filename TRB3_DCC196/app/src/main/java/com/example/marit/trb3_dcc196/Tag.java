package com.example.marit.trb3_dcc196;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by marit on 04/12/2017.
 */

public class Tag implements Serializable {
    private String valor;
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private int id;

    public Tag(String valor) {
        this.valor = valor;
    }

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public void insereTarefa(Tarefa t) {tarefas.add(t);}
    @Override
    public String toString() {
        return getValor();
    }
}
