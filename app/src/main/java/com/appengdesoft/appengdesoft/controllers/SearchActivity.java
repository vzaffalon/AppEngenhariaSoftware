package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Bolsas;

import java.util.ArrayList;

/**
 * Created by vzaffalon on 30/10/16.
 */

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private ArrayList<Bolsas> bolsas;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vagas);
        bolsas = new ArrayList<>();
        setUpToolbar();
        setUpRecyclerView();
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        finish();
        super.onBackPressed();  // optional depending on your needs
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                searchUsers(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        return true;
    }

    private void searchUsers(String query){

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
        toolbar.setTitle("Use a lupa para buscar");
    }

    private void setUpRecyclerView(){

        Bolsas bolsa = new Bolsas();
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);
        bolsa.setName("Redes Neurais Artificiais");
        bolsas.add(bolsa);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ListaVagasAdapter listaVagasAdapter = new ListaVagasAdapter(getApplicationContext(),bolsas,onClickList());
        recyclerView.setAdapter(listaVagasAdapter);
    }

    private ListaVagasAdapter.ListOnClickListener onClickList(){
        return new ListaVagasAdapter.ListOnClickListener(){
            @Override
            public void onClickList(View view, int idx) {
                Intent intent = new Intent(getApplicationContext(), InformacoesBolsaActivity.class);
                startActivity(intent);
            }
        };
    }
}
