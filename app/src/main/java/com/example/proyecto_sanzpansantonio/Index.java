package com.example.proyecto_sanzpansantonio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.security.cert.PKIXRevocationChecker;
import java.util.Optional;

public class Index extends AppCompatActivity implements View.OnLongClickListener {

    private TextView btnGames, btnSearch, btnCreateGame, btnCalendar, btnOptions;
    private String uid;
    private String email;
    SQLiteDatabase db = null;
    public static String FILENAME ="";
    private FirebaseAuth mAuth;
    private ImageButton btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        this.uid = Login.UID;
        this.email = Login.EMAIL;
        this.mAuth = FirebaseAuth.getInstance();
        createDDBB();

        this.btnLogOut = (ImageButton) findViewById(R.id.btnLogOut);

        btnLogOut   .setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                goLogin();
                return false;
            }
        });

    }

    public void onClickButton(View v) {
        Intent i = null;

        switch (v.getId()) {
            case R.id.btnGames:
                i = new Intent(this, MyGames.class);
                break;
            case R.id.btnSearch:
                i = new Intent(this, SearchGame.class);
                break;
            case R.id.btnCreate:
                i = new Intent(this, CreateGame.class);
                break;
            case R.id.btnCalendar:
                i = new Intent(this, Calendar.class);
                break;
            case R.id.btnOptions:
                i = new Intent(this, Options.class);
                break;
            default:
                i = new Intent(this, Login.class);
                break;
        }
        startActivity(i);
    }

    private void createDDBB() {
        BaseDatosHelper ddbb = new BaseDatosHelper(this, "PROYECTOANDROID", null, 1);
        db = ddbb.getWritableDatabase();
        insertNewUser();
    }

    private void insertNewUser() {
        Integer reg = 0;
        Cursor c = null;
        String query = "SELECT COUNT(*) FROM USER WHERE ID=?";
        c = db.rawQuery(query, new String[]{uid});

        if (c.moveToFirst()) {
            reg = c.getInt(0);
        }
        if (reg == 0) {
            String insertQuery = "INSERT INTO USER (ID, MAIL) VALUES(?, ?)";
            db.execSQL(insertQuery, new String[]{uid, email});
            System.out.println("Debug");
        } else return;

    }


    public void logOut(View view) {
        goLogin();
    }

    private void goLogin() {
        mAuth.signOut();
        Intent i = new Intent(this, Login.class);
        Login.UID = "";
        startActivity(i);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}