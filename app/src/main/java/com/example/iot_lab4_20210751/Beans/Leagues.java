package com.example.iot_lab4_20210751.Beans;

import java.io.Serializable;
import java.util.List;

public class Leagues implements Serializable {

    private int idLeague;
    private String strLeague;
    private String strSport;
    private List<String> strLeagueAlternate;

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public int getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(int idLeague) {
        this.idLeague = idLeague;
    }

    public List<String> getStrLeagueAlternate() {
        return strLeagueAlternate;
    }

    public void setStrLeagueAlternate(List<String> strLeagueAlternate) {
        this.strLeagueAlternate = strLeagueAlternate;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }
}
