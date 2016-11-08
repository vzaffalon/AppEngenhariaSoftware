package com.appengdesoft.appengdesoft.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Vaga extends RealmObject {
    private String data;
    private String area;
    private String cursos;
    private String requisitos;
    private String tipo;
    private Tcc tcc;
    private Pibic pibic;
    private Estagio estagio;
    private RealmList<User> users;


    public Vaga(String data, String area, String cursos, String requisitos,
                String tipo,Tcc tcc,Pibic pibic,Estagio estagio) {
        this.data = data;
        this.area = area;
        this.cursos = cursos;
        this.requisitos = requisitos;
        this.tipo = tipo;
        this.tcc = tcc;
        this.pibic = pibic;
        this.estagio = estagio;
    }

    public Vaga(){

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Tcc getTcc() {
        return tcc;
    }

    public void setTcc(Tcc tcc) {
        this.tcc = tcc;
    }

    public Pibic getPibic() {
        return pibic;
    }

    public void setPibic(Pibic pibic) {
        this.pibic = pibic;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public RealmList<User> getUser() {
        return users;
    }

    public void setUser(RealmList<User> users) {
        this.users = users;
    }
}
