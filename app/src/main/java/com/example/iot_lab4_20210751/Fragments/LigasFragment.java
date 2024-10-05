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

import com.example.iot_lab4_20210751.Adapters.LeaguesAdapter;
import com.example.iot_lab4_20210751.Beans.Leagues;
import com.example.iot_lab4_20210751.Beans.Team;
import com.example.iot_lab4_20210751.DTO.LeaguesDTO;
import com.example.iot_lab4_20210751.R;
import com.example.iot_lab4_20210751.Service.FootballService;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LigasFragment extends Fragment {

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

    }
}