package com.example.proyecto_sanzpansantonio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_sanzpansantonio.Modelos.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity {
    private SQLiteDatabase db = null;
    private String uid;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        this.uid = Login.UID;
        this.calendar = findViewById(R.id.calendarView);

        getGamesData();

    }

    public void backIndex(View v) {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }

    private void getGamesData() {
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
        }
        recDataInCalendar();
    }

    private void recDataInCalendar() {

    }
}