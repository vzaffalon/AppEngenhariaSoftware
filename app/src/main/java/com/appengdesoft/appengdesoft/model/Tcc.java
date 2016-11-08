package com.appengdesoft.appengdesoft.model;

import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Tcc extends RealmObject {

    private String assunto;

    public Tcc(String assunto) {
        this.assunto = assunto;
    }

    public Tcc(){

    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

}
