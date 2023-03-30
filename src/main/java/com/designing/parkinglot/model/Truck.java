package com.designing.parkinglot.model;

public class Truck extends Vehicle{
    public Truck(String vehNumber, String color) {
        super(vehNumber, color);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TRUCK;
    }
}
