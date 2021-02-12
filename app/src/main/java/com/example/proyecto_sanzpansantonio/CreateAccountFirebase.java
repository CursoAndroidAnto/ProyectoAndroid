package com.example.proyecto_sanzpansantonio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountFirebase extends AppCompatActivity implements View.OnClickListener {

    private EditText inMail, inMailC, inPass, inPassC;
    private Button btnReg;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_firebase);

        this.mAuth = FirebaseAuth.getInstance();
        this.inMail = findViewById(R.id.inUserMail);
        this.inMailC = findViewById(R.id.inUserMailConfirm);
        this.inPass = findViewById(R.id.inUserPassword);
        this.inPassC = findViewById(R.id.inUserPasswordConfirm);
        this.btnReg = findViewById(R.id.btnAcceptCreateUser);

        this.progressDialog = new ProgressDialog(this);

        //attaching listener to button
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        createAccount();
    }

    private void createAccount() {
        //Obtenemos el mail y la contraseña de las cajas de texto
        String email = inMail.getText().toString();
        String password = inPass.getText().toString();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Usuario vacío", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Contraseña vacía", Toast.LENGTH_LONG).show();
            return;
        }
        if (inMail.toString().equals(inMailC.toString())) {
            Toast.makeText(this, "El correo no coincide", Toast.LENGTH_LONG).show();
            return;
        }
        if (inPass.toString().equals(inPassC.toString())) {
            Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        createAccountFirebase(email, password);

    }

    private void createAccountFirebase(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateAccountFirebase.this, "Usuario registrado", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            redirectApp();
                            //updateUI(user);
                        } else {
                            String e = task.getException().toString();
                            Toast.makeText(CreateAccountFirebase.this, e, Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void redirectApp() {
        progressDialog.dismiss();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void backIndex(View v) {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }
}