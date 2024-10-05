package com.example.iot_lab4_20210751.Beans;

import java.io.Serializable;

public class Team implements Serializable {

    private int intRank;
    private String strTeam;
    private String strBadge;
    private int intWin;
    private int intLoss;
    private int intDraw;
    private int intGoalsFor;
    private int intGoalsAgainst;
    private int intGoalDifference;

    public int getIntRank() {
        return intRank;
    }

    public void setIntRank(int intRank) {
        this.intRank = intRank;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrBadge() {
        return strBadge;
    }

    public void setStrBadge(String strBadge) {
        this.strBadge = strBadge;
    }

    public int getIntWin() {
        return intWin;
    }

    public void setIntWin(int intWin) {
        this.intWin = intWin;
    }

    public int getIntLoss() {
        return intLoss;
    }

    public void setIntLoss(int intLoss) {
        this.intLoss = intLoss;
    }

    public int getIntDraw() {
        return intDraw;
    }

    public void setIntDraw(int intDraw) {
        this.intDraw = intDraw;
    }

    public int getIntGoalsFor() {
        return intGoalsFor;
    }

    public void setIntGoalsFor(int intGoalsFor) {
        this.intGoalsFor = intGoalsFor;
    }

    public int getIntGoalsAgainst() {
        return intGoalsAgainst;
    }

    public void setIntGoalsAgainst(int intGoalsAgainst) {
        this.intGoalsAgainst = intGoalsAgainst;
    }

    public int getIntGoalDifference() {
        return intGoalDifference;
    }

    public void setIntGoalDifference(int intGoalDifference) {
        this.intGoalDifference = intGoalDifference;
    }
}
