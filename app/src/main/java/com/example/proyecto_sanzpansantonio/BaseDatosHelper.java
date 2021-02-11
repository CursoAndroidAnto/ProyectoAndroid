package com.example.proyecto_sanzpansantonio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosHelper extends SQLiteOpenHelper {

    String sqlUserCreate = "CREATE TABLE USER(ID STRING , MAIL VARCHAR (40), USERNAME VARCHAR(40), NAME VARCHAR(40), LASTNAME VARCHAR(40), BDATE VARCHAR(40));";
    String sqlGameCreate = "CREATE TABLE GAME(ID INT, USERID VARCHAR(40), NAME VARCHAR(40), GAME_DATE VARCHAR(40), GAME_HOUR VARCHAR(40), GAME_DURATION INT, ADDRESS VARCHAR(40), MAX_PLAYERS INT);";
    String sqlGameUserCreate = "CREATE TABLE GAME_USER(ID INT, GAMEID INT, USERID VARCHAR(40));";

    public BaseDatosHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlUserCreate);
        db.execSQL(sqlGameCreate);
        db.execSQL(sqlGameUserCreate);

        System.out.println("Tablas creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS GAME");
        db.execSQL("DROP TABLE IF EXISTS GAMEUSER");

        db.execSQL(sqlUserCreate);
        db.execSQL(sqlGameCreate);
        db.execSQL(sqlGameUserCreate);

        System.out.println("Tablas actualizada");
    }
}
