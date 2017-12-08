package com.example.marit.trb2_dcc196;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by marit on 18/11/2017.
 */

public class Usuario implements Serializable{
    private String nome;
    private String email;
    private String horarioSaida;
    private String horarioEntrada;
    private int id;

    private ArrayList<Livro> livros = new ArrayList<>();

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.horarioSaida = null;
        this.horarioEntrada = null;
    }

    public Usuario() {}

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

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public void adicionaLivro(Livro l){
        this.livros.add(l);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
