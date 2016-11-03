package com.appengdesoft.appengdesoft.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Login;

/**
 * Created by vvieira on 03/11/2016.
 */
public class ListaVagasRHActivity extends Activity{

     ImageButton floatingButton;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUpButtons();
        setContentView(R.layout.vagas_rh_list_item);

    }

    @Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }

    private void setUpButtons(){
        floatingButton = (ImageButton) findViewById(R.id.adicionarvaga);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "botao clicado", Toast.LENGTH_LONG).show();
            }
        });

    }

//metodo que vai mandar essa tela pra tela de adicionar nova vaga, caso seja clicado o ImageButtom OU
// enviar para a tela da descricao da vaga

//    private void receiveResult(){
//        Intent intent = new Intent(getApplicationContext(),SelecionarTipoActivity.class);
//        startActivity(intent);
//    }


}
