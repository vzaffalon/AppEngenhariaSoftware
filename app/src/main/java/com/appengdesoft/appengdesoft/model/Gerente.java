package com.appengdesoft.appengdesoft.model;

import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Gerente extends RealmObject {

    private String empresa;


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
