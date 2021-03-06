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
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Aluno;
import com.appengdesoft.appengdesoft.model.Aplicacao;
import com.appengdesoft.appengdesoft.model.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vvieira on 08/11/2016.
 */

public class ListaAlunosActivity extends AppCompatActivity{
    private ArrayList<User> users;
    private ArrayList<Aplicacao> aplicacoes;
    private RecyclerView recyclerView;
    int idx;
    private User user;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos_professor);
        users = new ArrayList<>();
        aplicacoes = new ArrayList<>();
        idx = getIntent().getExtras().getInt("idx");
        setUpRecyclerView();
        setUpToolbar();

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    //metodo que configura a RecyclerView
    private void setUpRecyclerView() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<User> users_realm = realm.where(User.class)
                .equalTo("email",getUserEmail())
                .findAll();
        user = users_realm.get(0);
        try {
            if(user.getTipo().equals("Professor")) {
                users.addAll(user.getProfessor().getVagas().get(idx).getUser().subList(0, user.getProfessor().getVagas().get(idx).getUser().size()));
                aplicacoes.addAll(user.getProfessor().getVagas().get(idx).getAplicacaos().subList(0, user.getProfessor().getVagas().get(idx).getAplicacaos().size()));
            }else{
                users.addAll(user.getGerente().getVagas().get(idx).getUser().subList(0, user.getGerente().getVagas().get(idx).getUser().size()));
                aplicacoes.addAll(user.getGerente().getVagas().get(idx).getAplicacaos().subList(0, user.getGerente().getVagas().get(idx).getAplicacaos().size()));
            }
            }catch (Exception e){}
        realm.commitTransaction();
        realm.close();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ListaAlunosAdapter listaAlunosAdapter = new ListaAlunosAdapter(getApplicationContext(),users,aplicacoes,onClickList());
        recyclerView.setAdapter(listaAlunosAdapter);
    }


    private ListaAlunosAdapter.ListOnClickListener onClickList(){
        return new ListaAlunosAdapter.ListOnClickListener(){
            @Override
            public void onClickList(View view, int idx) {
                if(user.getTipo().equals("Professor")) {
                    Intent intent = new Intent(getApplicationContext(), InformacoesAlunoProfessorActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getApplicationContext(), com.appengdesoft.appengdesoft.controllers.professores.InformacoesAlunoGerenteActivity.class);
                    startActivity(intent);
                }
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
        toolbar.setTitle("Lista de alunos que aplicaram");
    }

    private String getUserEmail(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPrefs", android.content.Context.MODE_PRIVATE);
        return preferences.getString("email","");
    }

}
