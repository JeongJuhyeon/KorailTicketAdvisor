package kr.jjh.korailticketfinder;

import java.io.Serializable;

public class Ticket implements Serializable {
    String departure_station;
    String arrival_station;
    String departure_time;
    String arrival_time;

    public Ticket(String departure_station, String arrival_station, String departure_time, String arrival_time) {
        this.departure_station = departure_station;
        this.arrival_station = arrival_station;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }
}
