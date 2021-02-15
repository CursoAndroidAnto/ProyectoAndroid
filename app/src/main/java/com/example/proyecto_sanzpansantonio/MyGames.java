package com.example.proyecto_sanzpansantonio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.proyecto_sanzpansantonio.Modelos.Game;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MyGames extends AppCompatActivity {
    private SQLiteDatabase db = null;
    private RecyclerView rvMyGames;
    private EditText inMyGamesFilter;
    private RecyclerView.Adapter myAdapter;
    private String uid;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);

        Index.FILENAME = "MyGames";

        this.uid = Login.UID;
        this.rvMyGames = findViewById(R.id.rvMyGames);
        this.inMyGamesFilter = findViewById(R.id.inMyGamesFilter);
        this.mAuth = FirebaseAuth.getInstance();

        BaseDatosHelper gamelistbd = new BaseDatosHelper(getApplicationContext(), "PROYECTOANDROID", null, 1);
        db = gamelistbd.getReadableDatabase();

        String query = "SELECT ID, NAME, GAME_DATE, GAME_HOUR, GAME_DURATION, ADDRESS, MAX_PLAYERS FROM GAME WHERE USERID=?";
        Cursor c = db.rawQuery(query, new String[]{uid});

        ArrayList<Game> data = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Game g = new Game(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getInt(6));
                data.add(g);

            } while (c.moveToNext());

            this.myAdapter = new Adaptador(data);
            this.rvMyGames.setAdapter(this.myAdapter);
            this.rvMyGames.setHasFixedSize(true);
            this.rvMyGames.setLayoutManager(new LinearLayoutManager(this));
        }

        //Listener OnChanged del editbox filtro
        inMyGamesFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Antes
                System.out.println("Before");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Despues
                System.out.println("After");
            }
        });

    }

    public void goIndex(View v) {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }
    private void logOut(View view) {
        goLogin();
    }

    private void goLogin() {
        mAuth.signOut();
        Intent i = new Intent(this, Login.class);
        Login.UID = "";
        startActivity(i);
    }
}