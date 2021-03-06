package com.appengdesoft.appengdesoft.controllers.professores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;

/**
 * Created by vvieira on 08/11/2016.
 */

public class InformacoesAlunoProfessorActivity extends AppCompatActivity{


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_aluno);

        setUpToolbar();
        setUpButtons();
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
        toolbar.setTitle("Informações aluno");
    }

    private void setUpButtons(){
        ImageButton aceitarAluno = (ImageButton) findViewById(R.id.ib_aceitar_candidato);
        ImageButton negarAluno = (ImageButton) findViewById(R.id.ib_negar_candidato);

        aceitarAluno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Opção Confirmada",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        negarAluno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Opção Confirmada",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
