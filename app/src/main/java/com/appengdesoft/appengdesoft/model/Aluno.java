package com.appengdesoft.appengdesoft.model;

import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Aluno extends RealmObject {

    private String semestre;
    private String curso;
    private String matricula;

    public Aluno(String semestre, String curso, String matricula) {
        this.semestre = semestre;
        this.curso = curso;
        this.matricula = matricula;
    }

    public Aluno(){

    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
