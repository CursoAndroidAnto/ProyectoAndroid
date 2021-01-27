package com.example.proyecto_sanzpansantonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto_sanzpansantonio.Modelos.User;

public class CreateUser extends AppCompatActivity {
    TextView inUserName, inRealName, inLastName, inPassword, inConfirmPassword, inMail, inConfirmMail, inDate;
    Button btnAccept, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        inUserName = findViewById(R.id.inUserName);
        inRealName = findViewById(R.id.inRealName);
        inLastName = findViewById(R.id.inLastName);
        inPassword = findViewById(R.id.inUserPassword);
        inConfirmPassword = findViewById(R.id.inUserPasswordConfirm);
        inMail = findViewById(R.id.inUserMail);
        inConfirmMail = findViewById(R.id.inUserMailConfirm);
        inDate = findViewById(R.id.inUserDate);

    }

    public void onClickCreate(View view) {
        //COMPROBACIONES DE QUE ESTAN TODOS LOS CAMPOS RELLENOS Y CORRECTOS
        boolean checkPass = false;
        boolean checkMail = false;
        boolean checkNoNull = false;

        if (inUserName.getText().equals("")
                && inRealName.getText().equals("")
                && inLastName.getText().equals("")
                && inPassword.getText().equals("")
                && inMail.getText().equals("")
                && inDate.getText().equals("")) {
            checkNoNull = true;
        }

        if (inPassword.getText().toString().equals(inConfirmPassword.getText().toString())) {
            checkPass = true;
        }

        if (inMail.getText().toString().equals(inConfirmMail.getText().toString())) {
            checkMail = true;
        }

        //LLAMAR A CREAR USUARIO DESDE EL CONSTRUCTOR
        if (checkMail && checkPass && checkNoNull) {
            CreateUser();
        }
    }

    public void onClickCancel(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    private void CreateUser() {
        User user = new User(
                inUserName.getText().toString(),
                inRealName.getText().toString(),
                inLastName.getText().toString(),
                inPassword.getText().toString(),
                inMail.getText().toString(),
                Integer.parseInt(inDate.getText().toString())
        );
    }

}