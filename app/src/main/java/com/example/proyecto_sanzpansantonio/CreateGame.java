package com.example.proyecto_sanzpansantonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;

public class CreateGame extends AppCompatActivity {
    private TextView inputName, inputDate, inputHour, inputTime, inputAddress, inputPlayers;
    private Button btnDoc, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        inputName = findViewById(R.id.inputName);
        inputDate = findViewById(R.id.inputDate);
        inputHour = findViewById(R.id.inputHour);
        inputTime = findViewById(R.id.inputHour);
        inputAddress = findViewById(R.id.inputAddress);
        inputPlayers = findViewById(R.id.inputPlayers);
        btnDoc = findViewById(R.id.btnDoc);
        btnCreate = findViewById(R.id.btnCreate);
    }

    public void CreateGame(View view) {

        String name = inputName.getText().toString();
        //Date date = inputDate.getText();
        //Time hour = inputHour.getText();
        Integer gametime = Integer.parseInt(inputTime.getText().toString());
        String address = inputAddress.getText().toString();
        Integer players = Integer.parseInt(inputPlayers.getText().toString());

        // Class.Mod.createGameMod db = new Mod.createGameMod(name, date, hour, gametime, address, players)
    }

    public void backIndex(View v) {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }
}