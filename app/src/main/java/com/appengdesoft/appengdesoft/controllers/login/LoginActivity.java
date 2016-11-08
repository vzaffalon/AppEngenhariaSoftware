package com.appengdesoft.appengdesoft.controllers.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.controllers.alunos.SelecionarTipoActivity;
import com.appengdesoft.appengdesoft.controllers.cadastro.EscolherTipoContaActivity;
import com.appengdesoft.appengdesoft.controllers.gerente.ListaVagasRHActivity;
import com.appengdesoft.appengdesoft.controllers.professores.ListaVagasProfessorActivity;
import com.appengdesoft.appengdesoft.model.Aluno;
import com.appengdesoft.appengdesoft.model.Estagio;
import com.appengdesoft.appengdesoft.model.Gerente;
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
        TextView novaContaButton = (TextView) findViewById(R.id.ib_nova_conta);
        novaContaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EscolherTipoContaActivity.class);
                startActivity(intent);
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
            saveUser(email);
        }else{
            Toast.makeText(getApplicationContext(),"Usuario ou Senha Incorretos",Toast.LENGTH_SHORT).show();
        }
        realm.close();
    }

    private void saveUser(String email){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        editor.commit();
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
            Intent intent = new Intent(getApplicationContext(),ListaVagasProfessorActivity.class);
            startActivity(intent);
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
        Estagio estagio = new Estagio("R$ 950","Auxiliar Desenvolvimento de plataformas web utilizando o framework AngularJS" +
                "e BackEnd em Ruby On Rails");
        Vaga vaga = new Vaga("30/10/16","Estagio Desenvolvimento Web","Engenharias",
                "Minimo de 6 Semestre","estagio",null,null,estagio);
        realm.copyToRealm(vaga);

        estagio = new Estagio("R$ 950","Auxiliar Desenvolvimento de plataformas web utilizando o framework AngularJS" +
                "e BackEnd em Ruby On Rails");
        vaga = new Vaga("30/10/16","Estagio Desenvolvimento Web","Engenharia Mecanica",
                "Minimo de 6 Semestre","estagio",null,null,estagio);
        realm.copyToRealm(vaga);

        estagio = new Estagio("R$ 950","Auxiliar Desenvolvimento de plataformas web utilizando o framework AngularJS" +
                "e BackEnd em Ruby On Rails");
        vaga = new Vaga("30/10/16","Estagio Desenvolvimento Web","Engenharia de Soft",
                "Minimo de 6 Semestre","estagio",null,null,estagio);
        realm.copyToRealm(vaga);

        estagio = new Estagio("R$ 950","Auxiliar Desenvolvimento de plataformas web utilizando o framework AngularJS" +
                "e BackEnd em Ruby On Rails");
        vaga = new Vaga("30/10/16","Estagio Desenvolvimento Web","Engenharia Eletrica",
                "Minimo de 6 Semestre","estagio",null,null,estagio);
        realm.copyToRealm(vaga);



        Pibic pibic = new Pibic("R$ 500","Análise psicologica de crianças e auxilio de pessoas com necessidades");
        vaga = new Vaga("31/11/16","Psicologia Clinica","Psicologia",
                "Minimo de 7 Semestre","pibic",null,pibic,null);
        realm.copyToRealm(vaga);

        pibic = new Pibic("R$ 650","Estudo de plantas no meio do cerrado para preencher" +
                " catagalogo de plantas e descobrir informacoes para criação de novos remedios");
        vaga = new Vaga("25/11/16","Biologia","Biologia",
                "Minimo de 5 Semestre","pibic",null,pibic,null);
        realm.copyToRealm(vaga);

        pibic = new Pibic("R$ 700","Usar uma abordagem baseada no conhecimento pra identificar a influencia das tecnicas matematicas nas construções de castelos");
        vaga = new Vaga("28/11/16","Historia","Historia",
                "Minimo de 4 Semestre","pibic",null,pibic,null);
        realm.copyToRealm(vaga);

        pibic = new Pibic("R$ 800","Análise de series de fourier e aplicação das series na análise de circuitos eletricos com o auxilio de simuladores");
        vaga = new Vaga("29/11/16","Matematica","Matematica",
                "Minimo de 1 Semestre","pibic",null,pibic,null);
        realm.copyToRealm(vaga);

        Tcc tcc = new Tcc("Desenvolvimento de aplicativo para auxiliar busca de informações sobre rochas");
        vaga = new Vaga("31/11/16","Desenvolvimento de aplicativos","Ciencia da Computação",
                "Minimo de 2 Semestre","tcc",tcc,null,null);
        realm.copyToRealm(vaga);

        tcc = new Tcc("Desenvolvimento de aplicativo para criar um novo metodo de leitura de imagens usando qrcode");
        vaga = new Vaga("31/11/16","Desenvolvimento de aplicativos","Engenharia Eletrica",
                "Minimo de 6 Semestre","tcc",tcc,null,null);
        realm.copyToRealm(vaga);

        tcc = new Tcc("Desenvolvimento de site para mostrar informacoes de grafos de qualquer tipo desejado");
        vaga = new Vaga("31/11/16","Desenvolvimento de aplicativos","Ciencia da Computação",
                "Minimo de 4 Semestre","tcc",tcc,null,null);
        realm.copyToRealm(vaga);

        tcc = new Tcc("Criação de um framework para desenvolvimento web utilizando a linguagem swift");
        vaga = new Vaga("31/11/16","Desenvolvimento de aplicativos","Engenharia da Computação",
                "Minimo de 3 Semestre","tcc",tcc,null,null);
        realm.copyToRealm(vaga);


        realm.commitTransaction();
        realm.close();
    }

    private void setUpUsers(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Aluno aluno = new Aluno("Semestre 6","Engenharia de Computação","14012676");
        User user = new User("Bruno","José","aluno@gmail.com","123456","Aluno",aluno,null,null);
        realm.copyToRealm(user);
        realm.commitTransaction();

        realm.beginTransaction();
        Gerente gerente = new Gerente("House Mobile");
        user = new User("José","Pedro","gerente@gmail.com","123456","Gerente",null,gerente,null);
        realm.copyToRealm(user);
        realm.commitTransaction();

        realm.beginTransaction();
        Professor professor = new Professor("Universidade de Brasilia","Redes Neurais Artificiais",
                "Ciencia da Computação","27080831023");
        user = new User("Bruno","José","professor@gmail.com","123456","Professor",null,null,professor);
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
