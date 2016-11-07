package com.appengdesoft.appengdesoft.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Estagios;
import java.util.ArrayList;

/**
 * Created by vvieira on 03/11/2016.
 */
public class ListaVagasRHActivity extends Activity{

    private ImageButton floatingButton;
    private ArrayList<Estagios> estagios;
    private RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rh_vagas);
        estagios = new ArrayList<>();
        setUpButtons();
        setUpRecyclerView();

    }

    //metodo que configura a RecyclerView
    private void setUpRecyclerView() {
        Estagios estagio = new Estagios();
        estagio.setName("Vaga desenvolvedor Python");
        estagios.add(estagio);
        estagio.setName("Vaga desenvolvedor Python");
        estagios.add(estagio);
        estagio.setName("Vaga desenvolvedor Python");
        estagios.add(estagio);
        estagio.setName("Vaga desenvolvedor Python");
        estagios.add(estagio);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ListaVagasRHAdapter listaVagasRHAdapter = new ListaVagasRHAdapter(getApplicationContext(),estagios,onClickList());
        recyclerView.setAdapter(listaVagasRHAdapter);
    }

    @Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }

    private void setUpButtons(){
        floatingButton = (ImageButton) findViewById(R.id.adicionarvaga);

    }

    private ListaVagasRHAdapter.ListOnClickListener onClickList(){
        return new ListaVagasRHAdapter.ListOnClickListener(){
            @Override
            public void onClickList(View view, int idx) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        };
    }

//metodo que vai mandar essa tela pra tela de adicionar nova vaga, caso seja clicado o ImageButtom OU
// enviar para a tela da descricao da vaga

//    private void receiveResult(){
//        Intent intent = new Intent(getApplicationContext(),SelecionarTipoActivity.class);
//        startActivity(intent);
//    }


}
