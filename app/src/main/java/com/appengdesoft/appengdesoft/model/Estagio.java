package com.appengdesoft.appengdesoft.model;

import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Estagio extends RealmObject {

    private String valor_da_bolsa;


    public Estagio(String valor_da_bolsa) {
        this.valor_da_bolsa = valor_da_bolsa;
    }
    public Estagio(){

    }
    public String getValor_da_bolsa() {
        return valor_da_bolsa;
    }

    public void setValor_da_bolsa(String valor_da_bolsa) {
        this.valor_da_bolsa = valor_da_bolsa;
    }
}
