package com.designing.bookmyshow.model;

public abstract class Seat {
    private static int incrementer = 1;
    private String seatId;
    private int rowNo;
    private int colNo;
    private String screenId;

    public Seat(String screenId, int colNo, int rowNo) {
        this.seatId = "SEAT " + incrementer++;
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.screenId = screenId;
    }

    public abstract double getPrice();

    public String getSeatId() {
        return seatId;
    }

    public int getRowNo() {
        return rowNo;
    }

    public int getColNo() {
        return colNo;
    }

    @Override
    public String toString() {
        return String.format("seatId: %s, row: %s, col: %s", seatId, rowNo, colNo);
    }
}
