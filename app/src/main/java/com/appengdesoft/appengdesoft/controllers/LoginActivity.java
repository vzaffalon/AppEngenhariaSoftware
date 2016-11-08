package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Aluno;
import com.appengdesoft.appengdesoft.model.Estagio;
import com.appengdesoft.appengdesoft.model.Gerente;
import com.appengdesoft.appengdesoft.model.Login;
import com.appengdesoft.appengdesoft.model.Pibic;
import com.appengdesoft.appengdesoft.model.Professor;
import com.appengdesoft.appengdesoft.model.Tcc;
import com.appengdesoft.appengdesoft.model.User;
import com.appengdesoft.appengdesoft.model.Vaga;

import io.realm.Realm;
import io.realm.RealmResults;


public class LoginActivity extends AppCompatActivity {
    private EditText et_login;
    private EditText et_password;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpDatabase();
        setUpEditTexts();
        setUpButtons();
    }

    @Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }

    private void setUpButtons(){
        imageButton = (ImageButton) findViewById(R.id.ib_login);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login(et_login.getText().toString(),et_password.getText().toString());
            }
        });
    }

    private void setUpEditTexts(){
        et_login = (EditText) findViewById(R.id.et_login_login);
        et_password = (EditText) findViewById(R.id.et_login_password);
    }

    private void login(String email, String password){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<User> user = realm.where(User.class)
                .equalTo("email", email)
                .findAll();
        if(user.size()>0){
            receiveResult(user.get(0).getTipo());
        }else{
            Toast.makeText(getApplicationContext(),"Usuario ou Senha Incorretos",Toast.LENGTH_SHORT).show();
        }
        realm.close();
    }

    private void receiveResult(String type){
        if(type.equals("Gerente")){
            Intent intent = new Intent(getApplicationContext(),ListaVagasRHActivity.class);
            startActivity(intent);
        }
        if(type.equals("Aluno")){
            Intent intent = new Intent(getApplicationContext(),SelecionarTipoActivity.class);
            startActivity(intent);
        }
        if(type.equals("Professor")){

        }
    }

    private void setUpDatabase(){
        Realm.init(getApplicationContext());
        deleteDatabaseData();
        setUpUsers();
        setUpVagas();
    }

    private void setUpVagas(){
       // String data, String area, String cursos, String requisitos,
       //         String tipo,Tcc tcc, Pibic pibic, Estagio estagio

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Estagio estagio = new Estagio("R$ 800");
        Vaga vaga = new Vaga("30/10/16","Processamento de imagens","Engenharias",
                "Minimo de 6 Semestre","estagio",null,null,estagio);
        realm.copyToRealm(vaga);

        Pibic pibic = new Pibic("R$ 800","Análise psicologica de crianças");
        vaga = new Vaga("31/11/16","Psicologia Clinica","Psicologia",
                "Minimo de 8 Semestre","pibic",null,pibic,null);
        realm.copyToRealm(vaga);

        Tcc tcc = new Tcc("Desenvolvimento de aplicativo para auxiliar busca de informações sobre rochas");
        vaga = new Vaga("31/11/16","Desenvolvimento de aplicativos","Ciencia da Computação",
                "Minimo de 8 Semestre","tcc",tcc,null,null);
        realm.copyToRealm(vaga);


        realm.commitTransaction();
        realm.close();
    }

    private void setUpUsers(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Aluno aluno = new Aluno("Semestre 6","Engenharia de Computação","14012676");
        User user = new User("Bruno","José","brunojose@gmail.com","123456","Aluno",aluno,null,null);
        realm.copyToRealm(user);
        realm.commitTransaction();

        realm.beginTransaction();
        Gerente gerente = new Gerente("House Mobile");
        user = new User("José","Pedro","josepedro@gmail.com","123456","Gerente",null,gerente,null);
        realm.copyToRealm(user);
        realm.commitTransaction();

        realm.beginTransaction();
        Professor professor = new Professor("Universidade de Brasilia","Redes Neurais Artificiais",
                "Ciencia da Computação","27080831023");
        user = new User("Bruno","José","brunojose@gmail.com","123456","Aluno",null,null,professor);
        realm.copyToRealm(user);
        realm.commitTransaction();

        realm.close();
    }

    private void deleteDatabaseData(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

}
