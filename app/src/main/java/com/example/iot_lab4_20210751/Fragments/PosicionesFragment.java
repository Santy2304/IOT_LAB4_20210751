package com.example.iot_lab4_20210751.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.iot_lab4_20210751.Adapters.LeaguesAdapter;
import com.example.iot_lab4_20210751.Adapters.TeamAdapter;
import com.example.iot_lab4_20210751.Beans.Leagues;
import com.example.iot_lab4_20210751.Beans.Team;
import com.example.iot_lab4_20210751.DTO.LeaguesCountryDTO;
import com.example.iot_lab4_20210751.DTO.PosicionesDTO;
import com.example.iot_lab4_20210751.R;
import com.example.iot_lab4_20210751.Service.FootballService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PosicionesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posiciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        FootballService footballService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FootballService.class);

        Button buscarButton = view.findViewById(R.id.buscarButton);
        TextInputEditText searchLiga = view.findViewById(R.id.search_idLiga);
        TextInputEditText searchSeason = view.findViewById(R.id.search_Temporada);

        buscarButton.setOnClickListener(view1 -> {

            String idLiga = searchLiga.getText() != null ? searchLiga.getText().toString().trim() : "";
            String season = searchSeason.getText() != null ? searchSeason.getText().toString().trim() : "";

            if(idLiga.isEmpty() || season.isEmpty()){
                if(!season.isEmpty()){
                    Toast.makeText(requireContext(), "No se ingresó el id de la liga", Toast.LENGTH_LONG).show();
                }else if (!idLiga.isEmpty()){
                    Toast.makeText(requireContext(), "No se ingreso la temporada", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(requireContext(), "Ingreso los parámetros de LIGA y temporada", Toast.LENGTH_LONG).show();
                }
            }else {

                boolean valid = true;

                int idEnteroLiga = 0;
                try {
                    idEnteroLiga = Integer.parseInt(idLiga);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "El idLiga debe ser un número entero.", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                String seasonPattern = "^20\\d{2}-20\\d{2}$";
                if (!season.matches(seasonPattern)) {
                    Toast.makeText(requireContext(), "La temporada debe tener el formato 20XX-20XY.", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if(valid){
                    footballService.getPosiciones(Integer.parseInt(idLiga),season).enqueue(new Callback<PosicionesDTO>() {
                        @Override
                        public void onResponse(@NonNull Call<PosicionesDTO> call, @NonNull Response<PosicionesDTO> response) {
                            if (response.isSuccessful()) {
                                PosicionesDTO posicionesDTO = response.body();
                                Team[] teams = posicionesDTO.getTable();
                                TeamAdapter adapter = new TeamAdapter();
                                adapter.setContext(requireContext());
                                adapter.setListaPosiciones(Arrays.asList(teams));

                                RecyclerView recyclerView = view.findViewById(R.id.recycler);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

                            }else{
                                Log.d("msg-test", "error en la respuesta del webservice");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<PosicionesDTO> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }
            }

        });

        super.onViewCreated(view, savedInstanceState);
    }
}