package com.designing.parkinglot.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public abstract class Vehicle {
    private String vehNumber;
    private String color;
    public abstract VehicleType getVehicleType();
}
