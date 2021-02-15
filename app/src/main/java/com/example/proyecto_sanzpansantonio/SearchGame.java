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


        //BaseDatosHelper gamelistbd = new BaseDatosHelper(getApplicationContext(), "PROYECTOANDROID", null, 1);
        //db = gamelistbd.getReadableDatabase();

        initLoadRecycled();
        //String query = "SELECT ID, NAME, GAME_DATE, GAME_HOUR, GAME_DURATION, ADDRESS, MAX_PLAYERS FROM GAME";
        //Cursor c = db.rawQuery(query, null);
        //ArrayList<Game> data = new ArrayList<>();
        //if (c.moveToFirst()) {
        //    do {
        //        Game g = new Game(c.getInt(0),
        //                c.getString(1),
        //                c.getString(2),
        //                c.getString(3),
        //                c.getString(4),
        //                c.getString(5),
        //                c.getInt(6));
        //        data.add(g);

        //    } while (c.moveToNext());

        //    this.myAdapter = new Adaptador(data);
        //    this.rvSearchGame.setAdapter(this.myAdapter);
        //    this.rvSearchGame.setHasFixedSize(true);
        //    this.rvSearchGame.setLayoutManager(new LinearLayoutManager(this));

        //    Adaptador adapter = new Adaptador(data);
        //}

        inSearchGameFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Antes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Despues
                String newFilter = inSearchGameFilter.getText().toString();
                getFilterRecycled(newFilter);

            }
        });

    }

    private void initLoadRecycled() {
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
        }
    }

    private void getFilterRecycled(String filter) {
        BaseDatosHelper gamelistbd = new BaseDatosHelper(getApplicationContext(), "PROYECTOANDROID", null, 1);
        db = gamelistbd.getReadableDatabase();

        String filterQuery = filter;
        Cursor c;
        if (filterQuery.equals("")) {
            filterQuery = "SELECT ID, NAME, GAME_DATE, GAME_HOUR, GAME_DURATION, ADDRESS, MAX_PLAYERS FROM GAME";
            c = db.rawQuery(filterQuery, null);
        } else {
            filterQuery = "SELECT ID, NAME, GAME_DATE, GAME_HOUR, GAME_DURATION, ADDRESS, MAX_PLAYERS FROM GAME WHERE NAME LIKE ?";
            c = db.rawQuery(filterQuery, new String[]{"%" + filter + "%"});
        }

        ArrayList<Game> dataFiltered = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Game g = new Game(c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getInt(6));
                dataFiltered.add(g);

            } while (c.moveToNext());

            this.myAdapter = new Adaptador(dataFiltered);
            this.rvSearchGame.setAdapter(this.myAdapter);
            this.rvSearchGame.setHasFixedSize(true);
            this.rvSearchGame.setLayoutManager(new LinearLayoutManager(this));
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