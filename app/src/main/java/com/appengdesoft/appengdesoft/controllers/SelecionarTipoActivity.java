package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;

import java.util.ArrayList;

/**
 * Created by vzaffalon on 30/10/16.
 */

public class SelecionarTipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_tipo);
        setUpToolbar();
        setUpButtons();
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
        toolbar.setTitle("Selecionar Tipo De Busca");
    }

    private void setUpButtons(){
        RelativeLayout estagio_button = (RelativeLayout) findViewById(R.id.estagio_button);
        RelativeLayout pibic_button = (RelativeLayout) findViewById(R.id.pibic_button);
        RelativeLayout tcc_button = (RelativeLayout) findViewById(R.id.tcc_button);

        estagio_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.putExtra("tipo","estagio");
                startActivity(intent);
            }
        });

        pibic_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.putExtra("tipo","pibic");
                startActivity(intent);
            }
        });

        tcc_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                intent.putExtra("tipo","tcc");
                startActivity(intent);
            }
        });

    }
}
