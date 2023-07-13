package com.designing.bookmyshow.model;

public class RegularSeat extends Seat {

    public RegularSeat(String screenId,int seatId, int rowNo) {
        super(screenId,seatId, rowNo);
    }

    @Override
    public double getPrice() {
        return 200;
    }
}
