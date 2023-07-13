package com.designing.bookmyshow.model;

public class PremiumSeat extends Seat {

    public PremiumSeat(String screenId, int seatId, int rowNo) {
        super(screenId, seatId, rowNo);
    }
    @Override
    public double getPrice() {
        return 400.00;
    }
}
