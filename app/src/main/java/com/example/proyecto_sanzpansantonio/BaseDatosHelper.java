package com.example.proyecto_sanzpansantonio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosHelper extends SQLiteOpenHelper {

    String sqlUserCreate = "CREATE TABLE USER(ID STRING , MAIL VARCHAR (40), USERNAME VARCHAR(40), NAME VARCHAR(40), LASTNAME VARCHAR(40), BDATE VARCHAR(40));";
    String sqlGameCreate = "CREATE TABLE GAME(ID INT, USERID VARCHAR(40), NAME VARCHAR(40), GAME_DATE VARCHAR(40), GAME_HOUR VARCHAR(40), GAME_DURATION INT, ADDRESS VARCHAR(40), MAX_PLAYERS INT);";

    public BaseDatosHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlUserCreate);
        db.execSQL(sqlGameCreate);
        System.out.println("Tabla creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS GAME");
        db.execSQL(sqlUserCreate);
        System.out.println("Tabla actualizada");
    }
}
