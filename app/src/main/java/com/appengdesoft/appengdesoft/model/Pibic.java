package com.appengdesoft.appengdesoft.model;

import io.realm.RealmObject;

/**
 * Created by vzaffalon on 07/11/16.
 */

public class Pibic extends RealmObject {

    private String valor_da_bolsa;
    private String assunto;

    public Pibic(){

    }

    public Pibic(String valor_da_bolsa, String assunto) {
        this.valor_da_bolsa = valor_da_bolsa;
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getValor_da_bolsa() {
        return valor_da_bolsa;
    }

    public void setValor_da_bolsa(String valor_da_bolsa) {
        this.valor_da_bolsa = valor_da_bolsa;
    }
}
