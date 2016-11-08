package com.appengdesoft.appengdesoft.controllers.gerente;

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
import com.appengdesoft.appengdesoft.controllers.AdicionarVagaActivity;
import com.appengdesoft.appengdesoft.model.Estagios;
import java.util.ArrayList;

/**
 * Created by vvieira on 03/11/2016.
 */
public class ListaVagasRHActivity extends AppCompatActivity{

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

    @Override
    public void onResume(){
        super.onResume();
        Estagios estagio = new Estagios();
        estagio.setName("Vaga desenvolvedor Senior");
        estagios.add(estagio);
    }

    //metodo que configura a RecyclerView
    private void setUpRecyclerView() {
        Estagios estagio = new Estagios();
        estagio.setName("Vaga desenvolvedor Python");
        estagios.add(estagio);
        estagio.setName("Vaga desenvolvedor Java");
        estagios.add(estagio);
        estagio.setName("Vaga desenvolvedor Javascript");
        estagios.add(estagio);
        estagio.setName("Vaga desenvolvedor Android");
        estagios.add(estagio);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ListaVagasRHAdapter listaVagasRHAdapter = new ListaVagasRHAdapter(getApplicationContext(),estagios,onClickList());
        recyclerView.setAdapter(listaVagasRHAdapter);
    }

        private void setUpButtons(){
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent =new Intent(getApplicationContext(),AdicionarVagaActivity.class);
                    startActivity(intent);
                }
            });
        }

    private ListaVagasRHAdapter.ListOnClickListener onClickList(){
        return new ListaVagasRHAdapter.ListOnClickListener(){
            @Override
            public void onClickList(View view, int idx) {
                Toast.makeText(getApplicationContext(),"Mostrar Lista de Alunos que Aplicaram",Toast.LENGTH_SHORT).show();
            }
        };
    }

//metodo que vai mandar essa tela pra tela de adicionar nova vaga, caso seja clicado o ImageButtom OU
// enviar para a tela da descricao da vaga

//    private void receiveResult(){
//        Intent intent = new Intent(getApplicationContext(),SelecionarTipoActivity.class);
//        startActivity(intent);
//    }

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
        toolbar.setTitle("Lista de Vagas Rh");
    }


}
