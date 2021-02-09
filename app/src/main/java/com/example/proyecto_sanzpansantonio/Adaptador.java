package com.example.proyecto_sanzpansantonio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_sanzpansantonio.Modelos.Game;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<Game> games;

    public Adaptador(List<Game> games) {
        System.out.println("DEBUG Adaptador");
        this.games = games;
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
            txtPlayers = itemView.findViewById(R.id.lbCardPlayers);
            txtDate = itemView.findViewById(R.id.lbCardDate);
            txtHour = itemView.findViewById(R.id.lbCardHour);
        }
    }
}