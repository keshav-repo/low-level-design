package com.designing.parkinglot.model;

public class Car extends Vehicle{
    public Car(String vehNumber, String color) {
        super(vehNumber, color);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
