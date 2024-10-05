package com.example.iot_lab4_20210751.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20210751.Beans.Leagues;
import com.example.iot_lab4_20210751.R;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder>{
    private List<Leagues> listaLigas;
    private Context context;

    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.irv_leagues, parent, false);
        return new LeaguesAdapter.LeaguesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        Leagues l = listaLigas.get(position);
        holder.leagues = l;
        TextView textViewId = holder.itemView.findViewById(R.id.idLeague);
        textViewId.setText("#"+l.getIdLeague());

        TextView textViewName = holder.itemView.findViewById(R.id.Name);
        textViewName.setText(l.getStrLeague());

        TextView textViewAlternate = holder.itemView.findViewById(R.id.alternateNames);
        textViewAlternate.setText(l.getStrLeagueAlternate().replace(", ", " - "));

    }

    @Override
    public int getItemCount() {
        return listaLigas.size();
    }


    public class LeaguesViewHolder extends RecyclerView.ViewHolder{
        Leagues leagues;
        public LeaguesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Leagues> getListaLigas() {
        return listaLigas;
    }

    public void setListaLigas(List<Leagues> listaLigas) {
        this.listaLigas = listaLigas;
    }
}
