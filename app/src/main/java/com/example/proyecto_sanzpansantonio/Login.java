package com.example.proyecto_sanzpansantonio;

import android.os.Bundle;
import android.os.Debug;
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
    }
    private void Login(String user, String password){

    }
}
