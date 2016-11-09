package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Estagio;
import com.appengdesoft.appengdesoft.model.Pibic;
import com.appengdesoft.appengdesoft.model.Tcc;
import com.appengdesoft.appengdesoft.model.User;
import com.appengdesoft.appengdesoft.model.Vaga;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vzaffalon on 08/11/16.
 */

public class AdicionarVagaActivity extends AppCompatActivity {
    int id = 1;
    //1 for tcc and 2 for pibic

    boolean rh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_vaga_estagio);
        setUpButtons();
        setUpToolbar();
        rh = getIntent().getExtras().getBoolean("rh");
        RadioGroup radioButton = (RadioGroup) findViewById(R.id.radio_group);
        if(rh == true){
            radioButton.setVisibility(View.GONE);
        }
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
        MaterialEditText et_requisitos = (MaterialEditText) findViewById(R.id.et_requisitos);
        MaterialEditText et_salario = (MaterialEditText) findViewById(R.id.et_Salario);
        MaterialEditText et_descricao = (MaterialEditText) findViewById(R.id.et_descricao);

        String titulo = et_titulo.getText().toString();
        String curso = et_curso.getText().toString();
        String requisitos = et_requisitos.getText().toString();
        String salario = et_salario.getText().toString();
        String descricao = et_descricao.getText().toString();

        if(titulo.isEmpty() || curso.isEmpty() || requisitos.isEmpty() || salario.isEmpty() || descricao.isEmpty()){
            Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
        }else{
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmResults<User> users = realm.where(User.class)
                    .equalTo("email",getUserEmail())
                    .findAll();
            User user = users.get(0);
            if(user.getTipo().equals("Professor")){
                // String data, String area, String cursos, String requisitos,
                //         String tipo,Tcc tcc, Pibic pibic, Estagio estagio
                if(id==1){
                    Tcc tcc = new Tcc(descricao);
                    Vaga vaga = new Vaga("22/11/2016",titulo,curso,requisitos,"tcc",tcc,null,null);
                    user.getProfessor().getVagas().add(vaga);
                }else{
                    Pibic pibic = new Pibic(salario,descricao);
                    Vaga vaga = new Vaga("22/11/2016",titulo,curso,requisitos,"pibic",null,pibic,null);
                    user.getProfessor().getVagas().add(vaga);
                }
            }
            if(user.getTipo().equals("Gerente")){
                Estagio estagio = new Estagio(salario,descricao);
                Vaga vaga = new Vaga("22/11/2016",descricao,curso,
                        requisitos,"estagio",null,null,estagio);
                user.getGerente().getVagas().add(vaga);
            }

            realm.commitTransaction();
            Toast.makeText(getApplicationContext(),"Vaga Criada",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private String getUserEmail(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPrefs", android.content.Context.MODE_PRIVATE);
        return preferences.getString("email","");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pibic:
                if (checked)
                id = 1;
                    break;
            case R.id.radio_tcc:
                if (checked)
                id = 2;
                    break;
        }
    }

}