package com.example.proyecto_sanzpansantonio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextView txtUser, txtPassword, lbLoginError;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    static String UID;
    static String EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UID = "";
        this.mAuth = FirebaseAuth.getInstance();
        this.txtUser = findViewById(R.id.txtUser);
        this.txtPassword = findViewById(R.id.txtPassword);
        this.lbLoginError = findViewById(R.id.lbLoginError);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.btnLogin.setOnClickListener(this);
        this.progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        showProgressDialog("Login...");
        LoginFirebase();
    }

    private void LoginFirebase() {
        String email = txtUser.getText().toString();
        String password = txtPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Usuario vacío", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Contraseña vacía", Toast.LENGTH_LONG).show();
            return;
        }
        signInFirebaseUser(email, password);
    }

    private void signInFirebaseUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            UID = user.getUid();
                            EMAIL = user.getEmail();
                            Toast.makeText(Login.this, "Login success", Toast.LENGTH_LONG).show();
                            loginSuccess();
                        } else {
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void loginSuccess() {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }

    public void changeToCreateUser(View view) {
        Intent i = new Intent(this, CreateAccountFirebase.class);
        startActivity(i);
    }


    private void showProgressDialog(String text) {
        progressDialog.setMessage(text);
        progressDialog.show();
    }
}
