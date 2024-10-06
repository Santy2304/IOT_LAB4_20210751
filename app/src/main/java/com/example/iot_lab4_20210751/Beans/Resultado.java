package com.example.iot_lab4_20210751.Beans;

import java.io.Serializable;

public class Resultado implements Serializable {
    private String strLeague;
    private int intRound;
    private String strHomeTeam;
    private String strAwayTeam;
    private String dateEvent;
    private String strLeagueBadge;
    private int intAwayScore;
    private int intHomeScore;
    private String  intSpectators;

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public int getIntRound() {
        return intRound;
    }

    public void setIntRound(int intRound) {
        this.intRound = intRound;
    }

    public String getStrHomeTeam() {
        return strHomeTeam;
    }

    public void setStrHomeTeam(String strHomeTeam) {
        this.strHomeTeam = strHomeTeam;
    }

    public String getStrAwayTeam() {
        return strAwayTeam;
    }

    public void setStrAwayTeam(String strAwayTeam) {
        this.strAwayTeam = strAwayTeam;
    }

    public String getStrLeagueBadge() {
        return strLeagueBadge;
    }

    public void setStrLeagueBadge(String strLeagueBadge) {
        this.strLeagueBadge = strLeagueBadge;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public int getIntAwayScore() {
        return intAwayScore;
    }

    public void setIntAwayScore(int intAwayScore) {
        this.intAwayScore = intAwayScore;
    }

    public int getIntHomeScore() {
        return intHomeScore;
    }

    public void setIntHomeScore(int intHomeScore) {
        this.intHomeScore = intHomeScore;
    }

    public String getIntSpectators() {
        return intSpectators;
    }

    public void setIntSpectators(String intSpectators) {
        this.intSpectators = intSpectators;
    }
}
