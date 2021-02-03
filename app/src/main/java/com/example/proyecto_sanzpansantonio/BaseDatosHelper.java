package com.example.proyecto_sanzpansantonio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosHelper extends SQLiteOpenHelper {
    /*
    USER
    private Integer Id;
    private String Username;
    private String Name;
    private String LastName;
    private String Password;
    private String Mail;
    private Integer BDate;
     */
    /*
    GAME
            private String Name;
        private String GameDate;
        private String GameHour;
        private Integer GameTime;
        private String Address;
        private Integer MaxPlayers;
     */

    String sqlUserCreate = "CREATE TABLE USER(ID INT, USERNAME VARCHAR(40), NAME VARCHAR(40), LASTNAME VARCHAR(40), PASSWORD VARCHAR(40), MAIL VARCHAR (40), BDATE VARCHAR(40));";
    String sqlGameCreate = "CREATE TABLE GAME(ID INT, NAME VARCHAR(40), GAME_DATE VARCHAR(40), GAME_HOUR VARCHAR(40), GAME_DURATION INT, ADDRESS VARCHAR(40), MAX_PLAYERS INT);";

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
        db.execSQL("DROP TABLE IF EXISTS DOCTOR");
        db.execSQL(sqlUserCreate);
    }
}
