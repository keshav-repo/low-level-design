package com.designing.bookmyshow.model;

import java.util.UUID;

public abstract class Seat {
    private String seatId;

    private int seatNumber;
    private int rowNo;

    private boolean isBooked;

    public Seat(int seatId, int rowNo) {
        this.seatId = UUID.randomUUID().toString();
        this.rowNo = rowNo;
    }

    public abstract double getPrice();

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getSeatId() {
        return seatId;
    }
    public int getRowNo() {
        return rowNo;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", rowNo=" + rowNo +
                '}';
    }
}
