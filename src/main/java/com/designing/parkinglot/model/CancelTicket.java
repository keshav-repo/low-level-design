package com.designing.parkinglot.model;

public class CancelTicket {
   private Vehicle vehicle;

    public CancelTicket(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return String.format("Unparked vehicle with Registration Number: %s and Color: %s", vehicle.getVehNumber(), vehicle.getColor());
    }
}
