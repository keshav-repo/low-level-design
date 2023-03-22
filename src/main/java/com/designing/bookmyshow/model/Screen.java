package com.designing.bookmyshow.model;

import java.util.LinkedList;
import java.util.List;

public class Screen {

    private String hallId;

    List<Seat> seatsList;

    public Screen(String hallId) {
        this.hallId = hallId;
        this.seatsList = new LinkedList<>();
    }

    public String getHallId() {
        return hallId;
    }

    public List<Seat> getSeatsList() {
        return seatsList;
    }

    public void addSeats(Seat seat){
        this.seatsList.add(seat);
    }

    @Override
    public String toString() {
        return "Screen{" +
                "hallId='" + hallId + '\'' +
                '}';
    }
}
