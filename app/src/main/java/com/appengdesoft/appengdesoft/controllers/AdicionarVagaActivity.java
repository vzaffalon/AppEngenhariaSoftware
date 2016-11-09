package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.User;
import com.appengdesoft.appengdesoft.model.Vaga;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vzaffalon on 08/11/16.
 */

public class AdicionarVagaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_vaga_estagio);
        setUpButtons();
        setUpToolbar();
    }

    public void setUpButtons(){
        ImageButton imageButton = (ImageButton) findViewById(R.id.ib_criar_nova_vaga);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getData();
            }
        });
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
        toolbar.setTitle("Adicionando Vaga");
    }

    private void getData(){
        MaterialEditText et_titulo = (MaterialEditText) findViewById(R.id.et_titulo);
        MaterialEditText et_curso = (MaterialEditText) findViewById(R.id.et_curso);
        MaterialEditText et_semestre = (MaterialEditText) findViewById(R.id.et_semestre);
        MaterialEditText et_requisitos = (MaterialEditText) findViewById(R.id.et_requisitos);
        MaterialEditText et_salario = (MaterialEditText) findViewById(R.id.et_Salario);

        String titulo = et_titulo.getText().toString();
        String curso = et_curso.getText().toString();
        String semestre = et_semestre.getText().toString();
        String requisitos = et_requisitos.getText().toString();
        String salario = et_salario.getText().toString();

        if(titulo.isEmpty() || curso.isEmpty() || semestre.isEmpty() || requisitos.isEmpty() || salario.isEmpty()){
            Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
        }else{
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmResults<User> users = realm.where(User.class)
                    .equalTo("email",getUserEmail())
                    .findAll();
            User user = users.get(0);
            if(user.getTipo().equals("professor")){
                Vaga vaga = new Vaga();
                user.getProfessor().getVagas().add(vaga);
            }
            if(user.getTipo().equals("gerente")){

            }

            realm.commitTransaction();
            realm.close();
            Toast.makeText(getApplicationContext(),"Vaga Criada",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private String getUserEmail(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPrefs", android.content.Context.MODE_PRIVATE);
        return preferences.getString("email","");
    }

}