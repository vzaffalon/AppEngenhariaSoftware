package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Estagios;

import java.util.ArrayList;

/**
 * Created by vvieira on 03/11/2016.
 */
public class ListaVagasProfessorActivity extends AppCompatActivity{

    private ImageButton floatingButton;
    private ArrayList<Estagios> estagios;
    private RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rh_vaga);
        estagios = new ArrayList<>();
        setUpButtons();
        setUpRecyclerView();
        setUpToolbar();
    }

    //metodo que configura a RecyclerView
    private void setUpRecyclerView() {
        Estagios estagio = new Estagios();
        estagio.setName("Vaga Tcc Desenvolver Aplicativo");
        estagios.add(estagio);
        estagio.setName("Vaga Pibic Desenvolver Backend");
        estagios.add(estagio);
        estagio.setName("Vaga Tcc Data mining");
        estagios.add(estagio);
        estagio.setName("Vaga Pibic Montagem de Circuitos");
        estagios.add(estagio);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ListaVagasProfessorAdapter listaVagasProfessorAdapter = new ListaVagasProfessorAdapter(getApplicationContext(),estagios,onClickList());
        recyclerView.setAdapter(listaVagasProfessorAdapter);
    }

    private void setUpButtons(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cadastro Enviado",Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(getApplicationContext(),AdicionarVagaActivity.class);
                startActivity(intent);
            }
        });
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

    @Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.empty_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Lista de Vagas do Professor");
    }


}
