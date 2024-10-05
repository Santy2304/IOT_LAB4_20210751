package com.example.iot_lab4_20210751.DTO;

import com.example.iot_lab4_20210751.Beans.Leagues;

public class LeaguesCountryDTO {
    private Leagues[] countries;

    public Leagues[] getCountries() {
        return countries;
    }

    public void setCountries(Leagues[] countries) {
        this.countries = countries;
    }
}
