package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Vaga;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by vzaffalon on 30/10/16.
 */

public class InformacoesBolsaActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_vaga);
        setUpToolbar();
        setUpButtons();
        setUpLayout();
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
        toolbar.setTitle("Informações da Vaga");
    }

    private void setUpButtons(){
        ImageButton imageButton = (ImageButton) findViewById(R.id.ib_aplicar_para_vaga);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AplicarParaVagaActivity.class);
                startActivity(intent);
            }
        });
    }

    private Vaga getVaga(){
        String area = getIntent().getExtras().getString("area");

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Vaga> bolsa = realm.where(Vaga.class)
                .equalTo("area", area)
                .findAll();
        return bolsa.get(0);
    }

    private void setUpLayout(){
        Vaga vaga = getVaga();

        TextView tv_departamento = (TextView) findViewById(R.id.tv_departamento);
        TextView tv_area = (TextView) findViewById(R.id.tv_area);
        TextView tv_professor = (TextView) findViewById(R.id.tv_professor);
        TextView tv_semestre = (TextView) findViewById(R.id.tv_semestre);
        TextView tv_vagas_para = (TextView) findViewById(R.id.tv_vagas_para);
        TextView tv_descricao = (TextView) findViewById(R.id.tv_descricao);
        TextView tv_requisitos= (TextView) findViewById(R.id.tv_requisistos);
        TextView tv_valor = (TextView) findViewById(R.id.tv_valor_da_bolsa);

        tv_semestre.setVisibility(View.GONE);
        tv_area.setText(vaga.getArea());
        tv_requisitos.setText("Requisitos: " +vaga.getRequisitos());
        tv_vagas_para.setText("Vagas para alunos de: " + vaga.getCursos());
        if(vaga.getTipo().equals("pibic")){
            tv_descricao.setText("Descrição: "+ vaga.getPibic().getAssunto());
            tv_valor.setText("Bolsa: " + vaga.getPibic().getValor_da_bolsa());
            tv_professor.setText("Professor: " + "Jose Roberto");
            tv_departamento.setText("Departamento " +vaga.getCursos());
        }
        if(vaga.getTipo().equals("tcc")){
            tv_descricao.setText("Descrição: "+ vaga.getTcc().getAssunto());
            tv_valor.setVisibility(View.GONE);
            tv_professor.setText("Orientador: " + "Assis Pereira");
            tv_departamento.setText("Departamento " +vaga.getCursos());
        }
        if(vaga.getTipo().equals("estagio")){
            tv_descricao.setText("Descrição: "+ vaga.getEstagio().getDescricao());
            tv_valor.setText("Bolsa: " + vaga.getEstagio().getValor_da_bolsa());
            tv_professor.setText("Orientador: " + "Isabella Faraco");
            tv_departamento.setText("Vaga Estágio");
        }
    }
}
