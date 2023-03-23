package com.designing.bookmyshow.model;

import java.util.List;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private String showId;
    private List<String> seatBookedIdList;
    private String customerId;
    private BookingStatus bookingStatus;
    private String paymentId;

    private double bookingAmount;

    public Booking(String showId, List<String> seatBookedIdList, String customerId) {
        this.showId = showId;
        this.bookingId = UUID.randomUUID().toString();
        this.seatBookedIdList = seatBookedIdList;
        this.bookingStatus = BookingStatus.CREATED;
        this.customerId = customerId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatBookedIdList() {
        return seatBookedIdList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void conformBooking(){
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }
}
