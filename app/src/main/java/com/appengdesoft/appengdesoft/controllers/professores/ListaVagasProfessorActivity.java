package com.appengdesoft.appengdesoft.controllers.professores;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.appengdesoft.appengdesoft.model.User;
import com.appengdesoft.appengdesoft.model.Vaga;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vvieira on 03/11/2016.
 */
public class ListaVagasProfessorActivity extends AppCompatActivity{

    private ImageButton floatingButton;
    private ArrayList<Vaga> vagas;
    private RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rh_vaga);
        vagas = new ArrayList<>();
        setUpButtons();
        setUpToolbar();
        setUpRecyclerView();
    }

    @Override
    public void onResume(){
        super.onResume();
        vagas = new ArrayList<>();
        setUpRecyclerView();
    }

    //metodo que configura a RecyclerView
    private void setUpRecyclerView() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<User> users = realm.where(User.class)
                .equalTo("email",getUserEmail())
                .findAll();
        User user = users.get(0);
        try{
            vagas.addAll(user.getProfessor().getVagas().subList(0, user.getProfessor().getVagas().size()));
        }catch (Exception e){}
        realm.commitTransaction();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ListaVagasProfessorAdapter listaVagasProfessorAdapter = new ListaVagasProfessorAdapter(getApplicationContext(),vagas,onClickList());
        recyclerView.setAdapter(listaVagasProfessorAdapter);
    }

    private void setUpButtons(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AdicionarVagaActivity.class);
                intent.putExtra("rh",false);
                startActivity(intent);
            }
        });
    }

    private ListaVagasProfessorAdapter.ListOnClickListener onClickList(){
        return new ListaVagasProfessorAdapter.ListOnClickListener(){
            @Override
            public void onClickList(View view, int idx) {
                Intent intent = new Intent(getApplicationContext(), ListaAlunosActivity.class);
                intent.putExtra("idx",idx);
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

    private String getUserEmail(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPrefs", android.content.Context.MODE_PRIVATE);
        return preferences.getString("email","");
    }


}
