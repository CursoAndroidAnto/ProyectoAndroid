package com.example.proyecto_sanzpansantonio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

public class Index extends AppCompatActivity {

    private TextView btnGames, btnSearch, btnCreateGame, btnCalendar, btnOptions;
    private String uid;
    private String email;
    SQLiteDatabase db = null;
    //CommonMethods cm = new CommonMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        this.uid = Login.UID;
        this.email = Login.EMAIL;
        createDDBB();
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
}