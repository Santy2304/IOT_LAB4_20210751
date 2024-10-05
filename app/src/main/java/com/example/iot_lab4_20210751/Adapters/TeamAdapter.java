package com.example.iot_lab4_20210751.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iot_lab4_20210751.Beans.Leagues;
import com.example.iot_lab4_20210751.Beans.Team;
import com.example.iot_lab4_20210751.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamsViewHolder> {
    private List<Team> listaPosiciones;
    private Context context;

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.irv_posiciones, parent, false);
        return new TeamAdapter.TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Team t = listaPosiciones.get(position);
        holder.team = t;
        ImageView badgeImageView;
        TextView textViewId = holder.itemView.findViewById(R.id.idRank);
        textViewId.setText("#"+t.getIntRank());

        TextView textViewName = holder.itemView.findViewById(R.id.Name);
        textViewName.setText(t.getStrTeam());

        TextView textViewResultados = holder.itemView.findViewById(R.id.resultados);
        textViewResultados.setText("Resultados V/E/D: "+t.getIntWin()+"/"+t.getIntDraw()+"/"+t.getIntLoss());

        TextView textViewGoles = holder.itemView.findViewById(R.id.goles);
        textViewGoles.setText("Goles GF/GC/GD: "+t.getIntGoalsFor()+"/"+t.getIntGoalsAgainst()+"/"+t.getIntGoalDifference());

        Glide.with(holder.itemView.getContext())
                .load(t.getStrBadge())
                .into(holder.badgeImageView);

    }

    @Override
    public int getItemCount() {
        return listaPosiciones.size();
    }


    public class TeamsViewHolder extends RecyclerView.ViewHolder{
        Team team;
        ImageView badgeImageView;
        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            badgeImageView = itemView.findViewById(R.id.badgeImageView);
        }
    }

    public List<Team> getListaPosiciones() {
        return listaPosiciones;
    }

    public void setListaPosiciones(List<Team> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
