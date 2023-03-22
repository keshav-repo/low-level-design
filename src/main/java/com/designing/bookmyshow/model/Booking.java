package com.designing.bookmyshow.model;

import java.util.List;
import java.util.UUID;

public class Booking {



    private String bookingId;
    private Show show;
    private List<Seat> seatsBooked;
    private Customer customer;
    private BookingStatus bookingStatus;
    public Booking(Show show, List<Seat> seatsBooked, String customerId) {
        this.show = show;
        this.bookingId = UUID.randomUUID().toString();
        this.seatsBooked = seatsBooked;
        bookingStatus = BookingStatus.CREATED;
    }

    public String printTicket(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user name: ");
        stringBuilder.append(this.customer.getUserName());
        stringBuilder.append(this.bookingId);
        stringBuilder.append(this.show.toString());
        this.seatsBooked.forEach(seat -> {
            stringBuilder.append(seat.toString());
        });
        return stringBuilder.toString();
    }

}
