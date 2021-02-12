package com.example.proyecto_sanzpansantonio;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_sanzpansantonio.Modelos.DateValidator;
import com.example.proyecto_sanzpansantonio.Modelos.DateValidatorUsingDateFormat;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateGame extends AppCompatActivity {
    private TextView inName, inputDate, inputHour, inputDuration, inputAddress, inputPlayers, inDateD, inDateM, inDateY, inCGTimeH, inCGTimeM;
    private Button btnDoc, btnCreate;
    SQLiteDatabase db = null;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        this.inName = findViewById(R.id.inputName);
        //this.inputDate = findViewById(R.id.inputDate);
        this.inDateD = findViewById(R.id.inCGDateDD);
        this.inDateM = findViewById(R.id.inCGDateMM);
        this.inDateY = findViewById(R.id.inCGDateYYYY);
        this.inCGTimeH = findViewById(R.id.inCGTimeHH);
        this.inCGTimeM = findViewById(R.id.inCGTimeMM);
        //this.inputHour = findViewById(R.id.inputHour);
        this.inputDuration = findViewById(R.id.inputTime);
        this.inputAddress = findViewById(R.id.inputAddress);
        this.inputPlayers = findViewById(R.id.inputPlayers);
        this.btnDoc = findViewById(R.id.btnDoc);
        this.btnCreate = findViewById(R.id.btnCreate);

        this.mAuth = FirebaseAuth.getInstance();

        SQLiteDatabase db = null;
    }

    public void CreateGame(View view) {
        Integer dated = Integer.parseInt(inDateD.getText().toString());
        Integer datem = Integer.parseInt(inDateM.getText().toString());
        Integer datey = Integer.parseInt(inDateY.getText().toString());
        Integer dateh = Integer.parseInt(inCGTimeH.getText().toString());
        Integer datemin = Integer.parseInt(inCGTimeM.getText().toString());


        boolean validformatdate = checkValidFormatDate(dated, datem, datey, dateh, datemin);
        if (validformatdate) {
            insertGameDDBB();
        } else {
            Toast.makeText(this, "La fecha no es correcta", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertGameDDBB() {
        BaseDatosHelper ddbb = new BaseDatosHelper(this, "PROYECTOANDROID", null, 1);
        db = ddbb.getWritableDatabase();

        String date = inDateY.getText().toString()
                + "-"
                + inDateM.getText().toString()
                + "-"
                + inDateD.getText().toString();

        String hour = inCGTimeH.getText().toString()
                + ":"
                + inCGTimeM.getText().toString();

        ContentValues values = new ContentValues();
        values.put("ID", generateID());
        values.put("USERID", Login.UID);
        values.put("NAME", inName.getText().toString());
        values.put("GAME_DATE", date);
        values.put("GAME_HOUR", hour);
        values.put("GAME_DURATION", Integer.parseInt(inputDuration.getText().toString()));
        values.put("ADDRESS", inputAddress.getText().toString());
        values.put("MAX_PLAYERS", Integer.parseInt(inputPlayers.getText().toString()));

        System.out.println("DEBUG");
        db.insert("GAME", null, values);

        Toast.makeText(this, "Registro creado", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Index.class);
        startActivity(i);
        backIndex();
    }

    public void backIndex() {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
    }

    public void backIndex(View v) {
        backIndex();
    }

    private boolean checkValidFormatDate(int d, int m, int y, int h, int min) {
        boolean c = false;
        //convierte el dia y el mes de formato 1 a formato "01"
        String dStr = reformatDate(d);
        String mStr = reformatDate(m);
        //Crea el formato de la fecha como dd/MM/yyyy
        String strDate = dStr + "/" + mStr + "/" + y;
        //Valida que la fecha es correcta
        DateValidator validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
        c = validator.isValid(strDate);
        //Valido y genero una hora válida.
        String hhmm = createTimeFormat(h, min);
        //Obtiene la fecha actual
        String localdate = getLocalDate();
        //Crear fecha válida con hora
        String strDatehhmm = strDate + " " + hhmm + ":00";
        return c;
    }

    private String reformatDate(int p) {
        String pStr = p + "";
        if (pStr.length() == 1) {
            pStr = "0" + p;
        }
        return pStr;
    }

    private String getLocalDate() {
        String localDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        localDate = formatter.format(date);
        return localDate;
    }

    private String createTimeFormat(Integer h, Integer m) {
        String hhmm = "";
        if (h < 24 && h >= 0 && m < 60 && m >= 0) {
            String hh = reformatDate(h);
            String mm = reformatDate(m);
            hhmm = hh + ":" + mm;
        } else {
            System.out.println("ERROR");
            hhmm = "00:00:00";
        }
        return hhmm;
    }

    private String createValidUserDate(String date, String time) {
        String vd = date + " " + time + "00";
        return vd;
    }

    private Integer generateID() {
        Integer id = 1;
        Integer maxid = 0;
        String query = "SELECT MAX (ID) FROM GAME";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            maxid = c.getInt(0);
            id = maxid + 1;
        }
        return id;
    }

    //private Date parseStringToDate( String d){
    //Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(d);
    //}
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