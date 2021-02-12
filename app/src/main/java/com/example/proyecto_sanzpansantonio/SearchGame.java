package com.example.proyecto_sanzpansantonio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_sanzpansantonio.Modelos.Game;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchGame extends AppCompatActivity {
    private SQLiteDatabase db = null;
    private RecyclerView rvSearchGame;
    private EditText inSearchGameFilter;
    private RecyclerView.Adapter myAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_game);
        rvSearchGame = findViewById(R.id.rvSearchGame);
        inSearchGameFilter = findViewById(R.id.inSearchGameFilter);
        this.mAuth = FirebaseAuth.getInstance();


        Index.FILENAME = "SearchGame";


        BaseDatosHelper gamelistbd = new BaseDatosHelper(getApplicationContext(), "PROYECTOANDROID", null, 1);
        db = gamelistbd.getReadableDatabase();

        String query = "SELECT ID, NAME, GAME_DATE, GAME_HOUR, GAME_DURATION, ADDRESS, MAX_PLAYERS FROM GAME";
        Cursor c = db.rawQuery(query, null);
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
            this.rvSearchGame.setAdapter(this.myAdapter);
            this.rvSearchGame.setHasFixedSize(true);
            this.rvSearchGame.setLayoutManager(new LinearLayoutManager(this));

            Adaptador adapter = new Adaptador(data);
        }

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