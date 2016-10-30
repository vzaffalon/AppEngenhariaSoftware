package com.appengdesoft.appengdesoft.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appengdesoft.appengdesoft.R;
import com.appengdesoft.appengdesoft.model.Login;


public class LoginActivity extends AppCompatActivity {
    private EditText et_login;
    private EditText et_password;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    private void login(String username, String password){
        Login login = new Login(username,password);
        receiveResult();
    }

    private void receiveResult(){
        Intent intent = new Intent(getApplicationContext(),SelecionarTipoActivity.class);
        startActivity(intent);
    }

}
