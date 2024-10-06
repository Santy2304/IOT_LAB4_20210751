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

import com.example.iot_lab4_20210751.Adapters.ResultadosAdapter;
import com.example.iot_lab4_20210751.Adapters.TeamAdapter;
import com.example.iot_lab4_20210751.Beans.Resultado;
import com.example.iot_lab4_20210751.Beans.Team;
import com.example.iot_lab4_20210751.DTO.PosicionesDTO;
import com.example.iot_lab4_20210751.DTO.ResultadosDTO;
import com.example.iot_lab4_20210751.R;
import com.example.iot_lab4_20210751.Service.FootballService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultadosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resultados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FootballService footballService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FootballService.class);

        Button buscarButton = view.findViewById(R.id.buscarButton);
        TextInputEditText searchLiga = view.findViewById(R.id.search_idLiga);
        TextInputEditText searchSeason = view.findViewById(R.id.search_season);
        TextInputEditText searchRound = view.findViewById(R.id.search_ronda);

        buscarButton.setOnClickListener(view1 -> {

            String idLiga = searchLiga.getText() != null ? searchLiga.getText().toString().trim() : "";
            String season = searchSeason.getText() != null ? searchSeason.getText().toString().trim() : "";
            String round = searchRound.getText() != null ? searchRound.getText().toString().trim() : "";

            if(idLiga.isEmpty() || season.isEmpty() || round.isEmpty()){
                if(!season.isEmpty()){
                    Toast.makeText(requireContext(), "No se ingresó el id de la liga ni la ronda", Toast.LENGTH_LONG).show();
                }else if (!idLiga.isEmpty()){
                    Toast.makeText(requireContext(), "No se ingreso la temporada ni la ronda", Toast.LENGTH_LONG).show();
                }else if (!round.isEmpty()){
                    Toast.makeText(requireContext(), "No se ingresaron liga ni temporada", Toast.LENGTH_LONG).show();
                } else if (!season.isEmpty() && !round.isEmpty()) {
                    Toast.makeText(requireContext(), "No se ingresó liga", Toast.LENGTH_LONG).show();

                } else if (!round.isEmpty() && !idLiga.isEmpty()) {
                    Toast.makeText(requireContext(), "No se ingresó temporada", Toast.LENGTH_LONG).show();
                } else if (!season.isEmpty() && !idLiga.isEmpty()) {
                    Toast.makeText(requireContext(), "No se ingresó ronda", Toast.LENGTH_LONG).show();

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
                int idEnteroRound = 0;
                try {
                    idEnteroRound = Integer.parseInt(round);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "La ronda debe ser un número entero.", Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if(valid){
                    footballService.getResultados(Integer.parseInt(idLiga),Integer.parseInt(round),season).enqueue(new Callback<ResultadosDTO>() {
                        @Override
                        public void onResponse(@NonNull Call<ResultadosDTO> call, @NonNull Response<ResultadosDTO> response) {
                            if (response.isSuccessful()) {
                                ResultadosDTO resultadosDTO = response.body();
                                Resultado[] resultados = resultadosDTO.getEvents();
                                ResultadosAdapter adapter = new ResultadosAdapter();
                                adapter.setContext(requireContext());
                                adapter.setListaResultados(Arrays.asList(resultados));

                                RecyclerView recyclerView = view.findViewById(R.id.recycler);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

                            }else{
                                Log.d("msg-test", "error en la respuesta del webservice");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResultadosDTO> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }


            }
        });



        }
}