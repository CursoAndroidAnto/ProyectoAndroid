package com.example.proyecto_sanzpansantonio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private TextView txtUser, txtPassword, lbLoginError;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);

        lbLoginError = findViewById(R.id.lbLoginError);

    }

    public void onClickLogin(View view) {
        String username = txtUser.getText().toString();
        String password = txtPassword.getText().toString();
        login(username, password);
    }
    private void login(String user, String password){
        if (user.equals("anto") && password.equals("anto")){
            //AQUI VA UN SPINNER DE CARGA Y REDIRECCIONA A INDEX
            lbLoginError.setVisibility(View.INVISIBLE);
            loginSuccess();
        } else {
            lbLoginError.setTextColor(Color.RED);
            lbLoginError.setVisibility(View.VISIBLE);
        }
    }
    private void loginSuccess(){
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }
    public void changeToCreateUser(View view){
        Intent i = new Intent(this, CreateUser.class);
        startActivity(i);
    }
}
