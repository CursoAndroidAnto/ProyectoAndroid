package com.example.proyecto_sanzpansantonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto_sanzpansantonio.Modelos.DateValidator;
import com.example.proyecto_sanzpansantonio.Modelos.DateValidatorUsingDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateGame extends AppCompatActivity {
    private TextView inputName, inputDate, inputHour, inputDuration, inputAddress, inputPlayers, inDateD, inDateM, inDateY, inCGTimeH, inCGTimeM;
    private Button btnDoc, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        inputName = findViewById(R.id.inputName);
        //inputDate = findViewById(R.id.inputDate);
        inDateD = findViewById(R.id.inCGDateDD);
        inDateM = findViewById(R.id.inCGDateMM);
        inDateY = findViewById(R.id.inCGDateYYYY);
        inCGTimeH = findViewById(R.id.inCGTimeHH);
        inCGTimeM = findViewById(R.id.inCGTimeHH);
        //inputHour = findViewById(R.id.inputHour);
        inputDuration = findViewById(R.id.inputHour);
        inputAddress = findViewById(R.id.inputAddress);
        inputPlayers = findViewById(R.id.inputPlayers);
        btnDoc = findViewById(R.id.btnDoc);
        btnCreate = findViewById(R.id.btnCreate);
    }

    public void CreateGame(View view) {
        Integer dated = Integer.parseInt(inDateD.getText().toString());
        Integer datem = Integer.parseInt(inDateM.getText().toString());
        Integer datey = Integer.parseInt(inDateY.getText().toString());
        Integer dateh = Integer.parseInt(inCGTimeH.getText().toString());
        Integer datemin = Integer.parseInt(inCGTimeM.getText().toString());

        boolean validformatdate = checkValidFormatDate(dated, datem, datey, dateh, datemin);
        if (validformatdate) {

        } else {

        }
        // Class.Mod.createGameMod db = new Mod.createGameMod(name, date, hour, gametime, address, players)

        //createGameMod(String name, Date gameDate, Time gameHour, Integer gameTime, String address, Integer maxPlayers)

        //Game g = new Game(name, date, hour, gametime, address, players);
    }

    public void backIndex(View v) {
        Intent i = new Intent(this, Index.class);
        startActivity(i);
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

        //Comparar que la fecha proporcionada por el usuario es posterior a la fecha actual.


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
        if (h < 24 && h >= 0 && m < 60 && m >= 0){
            String hh = reformatDate(h);
            String mm = reformatDate(m);
            hhmm = hh + ":" + mm;
        }
        else{
            System.out.println("ERROR");
            hhmm = "00:00:00";
        }
        return hhmm;
    }

    private String createValidUserDate(String date, String time) {
        String vd = date + " " + time + "00";
        return vd;
    }

    //private Date parseStringToDate( String d){
        //Date parsedDate = new SimpleDateFormat("dd/MM/yyyy").parse(d);
    //}
}