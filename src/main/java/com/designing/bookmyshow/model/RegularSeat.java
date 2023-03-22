package com.designing.bookmyshow.model;

public class RegularSeat extends Seat {

    public RegularSeat(int seatId, int rowNo) {
        super(seatId, rowNo);
    }

    @Override
    public double getPrice() {
        return 200;
    }
}
