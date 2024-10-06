package com.example.iot_lab4_20210751.Service;

import com.example.iot_lab4_20210751.Beans.Leagues;
import com.example.iot_lab4_20210751.DTO.LeaguesCountryDTO;
import com.example.iot_lab4_20210751.DTO.LeaguesDTO;
import com.example.iot_lab4_20210751.DTO.PosicionesDTO;
import com.example.iot_lab4_20210751.DTO.ResultadosDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FootballService {

    @GET("/api/v1/json/3/all_leagues.php")
    Call<LeaguesDTO> getAllTeams();

    @GET("/api/v1/json/3/search_all_leagues.php")
    Call<LeaguesCountryDTO> getCountryTeams(@Query("c") String country);

    @GET("/api/v1/json/3/lookuptable.php")
    Call<PosicionesDTO> getPosiciones(@Query("l") int idLiga, @Query("s") String temporada);

    @GET("/api/v1/json/3/eventsround.php")
    Call<ResultadosDTO> getResultados(@Query("id") int idLiga, @Query("r") int round, @Query("s") String temporada);



}
