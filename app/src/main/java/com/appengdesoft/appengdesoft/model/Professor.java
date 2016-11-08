package com.appengdesoft.appengdesoft.model;

import android.widget.ProgressBar;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Professor extends RealmObject {

    private String universidade;
    private String area_de_pesquisa;
    private String departamento;
    private String matricula;
    private RealmList<Vaga> vagas;

    public Professor(String universidade, String area_de_pesquisa, String departamento, String matricula) {
        this.universidade = universidade;
        this.area_de_pesquisa = area_de_pesquisa;
        this.departamento = departamento;
        this.matricula = matricula;
    }

    public Professor(){

    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getArea_de_pesquisa() {
        return area_de_pesquisa;
    }

    public void setArea_de_pesquisa(String area_de_pesquisa) {
        this.area_de_pesquisa = area_de_pesquisa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public RealmList<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(RealmList<Vaga> vagas) {
        this.vagas = vagas;
    }


}
