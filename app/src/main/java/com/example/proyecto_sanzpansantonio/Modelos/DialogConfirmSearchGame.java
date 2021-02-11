package com.example.proyecto_sanzpansantonio.Modelos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;

import com.example.proyecto_sanzpansantonio.Adaptador;
import com.example.proyecto_sanzpansantonio.SearchGame;


public class DialogConfirmSearchGame extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("¿Desea apuntarse a esta partida?")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {


                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {


                            }
                        }
                )
                .create();
    }
}