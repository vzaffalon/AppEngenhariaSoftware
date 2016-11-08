package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;

/**
 * Created by vzaffalon on 08/11/16.
 */

public class EscolherTipoContaActivity extends AppCompatActivity {
    int id = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_conta);
        setUpToolbar();

        Button button = (Button) findViewById(R.id.button_criar_conta);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(id == 1){
                    Intent intent =new Intent(getApplicationContext(),CadastroProfessorActivity.class);
                    startActivity(intent);
                }
                if(id == 2){
                    Intent intent =new Intent(getApplicationContext(),CadastroEmpresaActivity.class);
                    startActivity(intent);
                }
                if(id == 3){
                    Intent intent =new Intent(getApplicationContext(),CadastroAlunoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_professor:
                if (checked)
                    // Pirates are the best
                    id = 1;
                    break;
            case R.id.radio_empresa:
                if (checked)
                    // Ninjas rule
                    id = 2;
                    break;
            case R.id.radio_aluno:
                if (checked)
                    // Ninjas rule
                    id = 3;
                    break;
        }
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
        toolbar.setTitle("Cadastro");
    }

}
