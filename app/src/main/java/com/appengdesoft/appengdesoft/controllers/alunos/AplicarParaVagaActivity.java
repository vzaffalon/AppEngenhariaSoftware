package com.appengdesoft.appengdesoft.controllers.alunos;

import android.content.Context;
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
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by vzaffalon on 30/10/16.
 */

public class AplicarParaVagaActivity extends AppCompatActivity {

    private String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicar_para_vaga);
        setUpToolbar();
        setUpButtons();
        area = getIntent().getExtras().getString("area");
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
        toolbar.setTitle("Preencha as informações");
    }

    private void setUpButtons(){
        ImageButton imageButton = (ImageButton) findViewById(R.id.ib_aplicar_para_vaga);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData(){
        MaterialEditText et_nome = (MaterialEditText) findViewById(R.id.et_nome);
        MaterialEditText et_curso = (MaterialEditText) findViewById(R.id.et_curso);
        MaterialEditText et_semestre = (MaterialEditText) findViewById(R.id.et_semestre);
        MaterialEditText et_habilidades = (MaterialEditText) findViewById(R.id.et_habilidades);
        MaterialEditText et_contato = (MaterialEditText) findViewById(R.id.et_contato);

        String nome = et_nome.getText().toString();
        String curso = et_curso.getText().toString();
        String semestre = et_semestre.getText().toString();
        String habilidade = et_habilidades.getText().toString();
        String contato = et_contato.getText().toString();

        if(nome.isEmpty() || curso.isEmpty() || semestre.isEmpty() || habilidade.isEmpty() || contato.isEmpty()){
            Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
        }else{
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmResults<Vaga> bolsa = realm.where(Vaga.class)
                    .equalTo("area",area)
                    .findAll();
            RealmList<User> users_vaga = bolsa.get(0).getUser();
            RealmResults<User> users_list = realm.where(User.class)
                    .equalTo("email",getUserEmail())
                    .findAll();
            users_vaga.add(users_list.get(0));
            realm.commitTransaction();
            realm.close();

            Toast.makeText(getApplicationContext(),"Vaga aplicada com sucesso",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private String getUserEmail(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("email","");
    }

}