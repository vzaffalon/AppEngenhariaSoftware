package com.appengdesoft.appengdesoft.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Gerente extends RealmObject {

    private String empresa;
    private RealmList<Vaga> vagas;

    public RealmList<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(RealmList<Vaga> vagas) {
        this.vagas = vagas;
    }

    public Gerente(String empresa) {
        this.empresa = empresa;
    }

    public Gerente(){

    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }


}
