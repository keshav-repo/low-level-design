package com.designing.parkinglot.model;

public class Motorcycle extends Vehicle{
    public Motorcycle(String vehNumber, String color) {
        super(vehNumber, color);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.MOTORCYCLE;
    }
}
