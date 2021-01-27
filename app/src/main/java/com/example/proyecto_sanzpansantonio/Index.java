package com.example.proyecto_sanzpansantonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Index extends AppCompatActivity {
    private TextView btnGames, btnSearch, btnCreateGame, btnCalendar, btnOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    public void onClickButton(View v){
        switch  (v.getId()){
            case R.id.btnGames:
                goMyGames();
                break;
            case R.id.btnSearch:
                goSearch();
                break;
            case R.id.btnCreate:
                goCreateGame();
                break;
            case R.id.btnCalendar:
                goCalendar();
                break;
            case R.id.btnOptions:
                goOptions();
                break;
        }
    }

    private void goMyGames(){
        Intent i = new Intent(this, MyGames.class);
        startActivity(i);
    }
    private void goSearch(){
        Intent i = new Intent(this, SearchGame.class);
        startActivity(i);
    }
    private void goCreateGame(){
        Intent i = new Intent(this, CreateGame.class);
        startActivity(i);
    }
    private void goCalendar(){
        Intent i = new Intent(this, Calendar.class);
        startActivity(i);
    }
    private void goOptions(){


    }
}