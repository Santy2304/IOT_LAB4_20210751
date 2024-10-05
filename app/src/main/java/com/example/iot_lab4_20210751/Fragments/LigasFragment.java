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
import com.example.iot_lab4_20210751.Beans.Leagues;
import com.example.iot_lab4_20210751.Beans.Team;
import com.example.iot_lab4_20210751.DTO.LeaguesCountryDTO;
import com.example.iot_lab4_20210751.DTO.LeaguesDTO;
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

public class LigasFragment extends Fragment {
    List<String> validCountries = Arrays.asList("Spain", "England", "France", "Brazil", "Germany");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ligas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FootballService footballService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FootballService.class);


        Button buscarButton = view.findViewById(R.id.buscarButton);
        TextInputEditText searchLiga = view.findViewById(R.id.search_liga);

        buscarButton.setOnClickListener(view1 -> {
            String pais = searchLiga.getText() != null ? searchLiga.getText().toString().trim() : "";

            if(pais.isEmpty()){
                footballService.getAllTeams().enqueue(new Callback<LeaguesDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<LeaguesDTO> call, @NonNull Response<LeaguesDTO> response) {
                        if (response.isSuccessful()) {
                            LeaguesDTO leaguesDTO = response.body();
                            Leagues[] ligas = leaguesDTO.getLeagues();
                            LeaguesAdapter adapter = new LeaguesAdapter();
                            adapter.setContext(requireContext());
                            adapter.setListaLigas(Arrays.asList(ligas));

                            RecyclerView recyclerView = view.findViewById(R.id.recycler);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

                        }else{
                            Log.d("msg-test", "error en la respuesta del webservice");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LeaguesDTO> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }else {

                if (validCountries.contains(pais)){
                    footballService.getCountryTeams(pais).enqueue(new Callback<LeaguesCountryDTO>() {
                        @Override
                        public void onResponse(@NonNull Call<LeaguesCountryDTO> call, @NonNull Response<LeaguesCountryDTO> response) {
                            if (response.isSuccessful()) {
                                LeaguesCountryDTO leaguesCountryDTO = response.body();
                                Leagues[] ligas = leaguesCountryDTO.getCountries();
                                LeaguesAdapter adapter = new LeaguesAdapter();
                                adapter.setContext(requireContext());
                                adapter.setListaLigas(Arrays.asList(ligas));

                                RecyclerView recyclerView = view.findViewById(R.id.recycler);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

                            }else{
                                Log.d("msg-test", "error en la respuesta del webservice");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<LeaguesCountryDTO> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }else{
                    Toast.makeText(requireContext(), "El país no está disponible o está mal escrito", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}