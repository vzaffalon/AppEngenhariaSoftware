package com.appengdesoft.appengdesoft.model;

import io.realm.RealmObject;

/**
 * Created by vzaffalon on 10/11/16.
 */

public class Aplicacao  extends RealmObject{

    String nome;
    String curso;
    String semeste;
    String habilidade;
    String contato;
    String type;

    public Aplicacao(String nome, String curso, String semeste, String habilidade, String contato,String type) {
        this.nome = nome;
        this.curso = curso;
        this.semeste = semeste;
        this.habilidade = habilidade;
        this.contato = contato;
        this.type = type;
    }

    public Aplicacao(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSemeste() {
        return semeste;
    }

    public void setSemeste(String semeste) {
        this.semeste = semeste;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
