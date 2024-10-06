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
import com.example.iot_lab4_20210751.Beans.Resultado;
import com.example.iot_lab4_20210751.Beans.Team;
import com.example.iot_lab4_20210751.R;

import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadosViewHolder>{

    private List<Resultado> listaResultados;
    private Context context;

    @NonNull
    @Override
    public ResultadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.irv_resultados, parent, false);
        return new ResultadosAdapter.ResultadosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadosViewHolder holder, int position) {
        Resultado r = listaResultados.get(position);
        holder.resultado = r;
        TextView textViewRound = holder.itemView.findViewById(R.id.idRound);
        textViewRound.setText("# "+r.getIntRound());

        TextView textViewHomeScore = holder.itemView.findViewById(R.id.homeScore);
        textViewHomeScore.setText(r.getIntHomeScore()+"");

        TextView textViewHomeName = holder.itemView.findViewById(R.id.homeName);
        textViewHomeName.setText(r.getStrHomeTeam());

        TextView textViewAwayScore = holder.itemView.findViewById(R.id.AwayScore);
        textViewAwayScore.setText(r.getIntAwayScore()+"");

        TextView textViewAwayName = holder.itemView.findViewById(R.id.awayName);
        textViewAwayName.setText(r.getStrAwayTeam());

        TextView textViewCantEspectadores = holder.itemView.findViewById(R.id.cantEspectadores);
        String ola = r.getIntSpectators()==null?"0":r.getIntSpectators();
        textViewCantEspectadores.setText("Espectadores: "+ola);

        TextView textViewDate = holder.itemView.findViewById(R.id.date);
        textViewDate.setText(r.getDateEvent());

        TextView textViewLeague = holder.itemView.findViewById(R.id.leagueName);
        textViewLeague.setText(r.getStrLeague());

        Glide.with(holder.itemView.getContext())
                .load(r.getStrLeagueBadge())
                .into(holder.badgeImageView);
    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

    public class ResultadosViewHolder extends RecyclerView.ViewHolder{
        Resultado resultado;
        ImageView badgeImageView;

        public ResultadosViewHolder(@NonNull View itemView) {
            super(itemView);
            badgeImageView = itemView.findViewById(R.id.badgeImageView);

        }
    }


    public List<Resultado> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<Resultado> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
