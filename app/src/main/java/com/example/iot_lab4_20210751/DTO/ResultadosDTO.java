package com.example.iot_lab4_20210751.DTO;

import com.example.iot_lab4_20210751.Beans.Resultado;

public class ResultadosDTO {
    private Resultado[] events;

    public Resultado[] getEvents() {
        return events;
    }

    public void setEvents(Resultado[] events) {
        this.events = events;
    }
}
