package com.example.proyecto_sanzpansantonio;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_sanzpansantonio.Modelos.Game;
import com.example.proyecto_sanzpansantonio.Modelos.DialogConfirmSearchGame;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<Game> games;
    private View.OnClickListener listener;
    private Game selectedGame;
    SQLiteDatabase db = null;

    public Adaptador(List<Game> games) {
        System.out.println("DEBUG Adaptador");
        this.games = games;
        //SQLiteDatabase db = null;
    }

    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("DEBUG onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        System.out.println("DEBUG onBindViewHolder");

        String name = games.get(position).getName();
        Integer players = games.get(position).getMaxPlayers();
        String date = games.get(position).getGameDate();
        String hour = games.get(position).getGameHour();

        holder.txtNombre.setText(name);
        holder.txtPlayers.setText(players.toString());
        holder.txtDate.setText(date);
        holder.txtHour.setText(hour);

        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        System.out.println("Click!");
        //    }
        //});
        holder.setOnClickListener(games.get(position));

    }

    @Override
    public int getItemCount() {
        System.out.println("DEBUG getItemCount");
        return games.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre;
        private TextView txtPlayers;
        private TextView txtDate;
        private TextView txtHour;

        public ViewHolder(View itemView) {
            super(itemView);
            System.out.println("DEBUG ViewHolder");
            txtNombre = itemView.findViewById(R.id.lbCardName);
            txtPlayers = itemView.findViewById(R.id.lbCardDate);
            txtDate = itemView.findViewById(R.id.lbCardDate);
            txtHour = itemView.findViewById(R.id.lbCardHour);
        }

        public void setOnClickListener(Game game) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    switch (Index.FILENAME) {
                        case "SearchGame":
                            showConfirmDialog(game);
                            break;
                        case "MyGames":
                            showInfoDialog(game);
                            break;
                    }
                }
            });
        }

        public void showInfoDialog(Game game) {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());

            final String[] items = new String[6];
            items[0] = "Dirección: " + game.getAddress();
            items[1] = "Fecha: " + game.getGameDate();
            items[2] = "Hora: " + game.getGameHour();
            items[3] = "Duración estimada (H): " + game.getGameDuration();
            items[4] = "Maximo de jugadores: " + game.getMaxPlayers().toString();
            items[5] = "Jugadores apuntados: " + getJoinedPlayers(game).toString();

            builder.setTitle(game.getName());
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        private Integer getJoinedPlayers(Game game) {
            BaseDatosHelper ddbb = new BaseDatosHelper(itemView.getContext(), "PROYECTOANDROID", null, 1);
            SQLiteDatabase db = ddbb.getWritableDatabase();
            String query = "SELECT COUNT(GAMEID) FROM GAME_USER WHERE GAMEID=?";
            Cursor c = db.rawQuery(query, new String[]{game.getId().toString()});
            Integer joinedPlayers = 0;
            if (c.moveToFirst()) {
                do {
                    joinedPlayers = c.getInt(0);
                } while (c.moveToNext());
            }
            return joinedPlayers;
        }

        public void showConfirmDialog(Game game) {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Confirm");
            builder.setMessage("¿Quieres apuntarte a la partida '" + game.getName() +
                    "' " + "el dia " + game.getGameDate() +
                    "?");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmDialogBtn(game);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancelDialogBtn();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private void confirmDialogBtn(Game game) {
            System.out.println("Confirm" + game.getName());
            createNewRegGU(game);
        }

        private void cancelDialogBtn() {
            System.out.println("Cancel");
        }

        private void createNewRegGU(Game game) {

            Integer joinedPlayers = getJoinedPlayers(game);

            if (joinedPlayers < game.getMaxPlayers()) {
                SQLiteDatabase db = null;

                BaseDatosHelper ddbb = new BaseDatosHelper(itemView.getContext(), "PROYECTOANDROID", null, 1);
                db = ddbb.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("ID", generateID());
                values.put("GAMEID", game.getId());
                values.put("USERID", Login.UID);

                db.insert("GAME_USER", null, values);
                Toast.makeText(itemView.getContext(), "Registro creado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(itemView.getContext(), "ERROR: Número máximo de jugadores alcanzado", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        private Integer generateID() {
            BaseDatosHelper ddbb = new BaseDatosHelper(itemView.getContext(), "PROYECTOANDROID", null, 1);
            SQLiteDatabase db = ddbb.getWritableDatabase();
            ;
            Integer id = 1;
            Integer maxid = 0;
            String query = "SELECT MAX (ID) FROM GAME_USER";

            Cursor c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                maxid = c.getInt(0);
                id = maxid + 1;
            }
            return id;
        }

    }
}