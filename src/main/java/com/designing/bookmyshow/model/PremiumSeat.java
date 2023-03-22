package com.designing.bookmyshow.model;

public class PremiumSeat extends Seat {

    public PremiumSeat(int seatId, int rowNo) {
        super(seatId, rowNo);
    }
    @Override
    public double getPrice() {
        return 400.00;
    }
}
