package com.example.projetolistview;

import java.io.Serializable;
import java.util.Comparator;

public class Pessoa implements Serializable {
    private String nome;
    private String sobrenome;
    private String curso;

    // construtor vazio
    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, String curso) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDados() {
        return "Nome: " + nome + " " + sobrenome + "\nCurso: " + curso;
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}

// vamos criar uma classe interna para ordenar os objetos
class OrdenaPorNome implements Comparator<Pessoa> {

    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        return p1.getNome().compareTo(p2.getNome());
    }
}