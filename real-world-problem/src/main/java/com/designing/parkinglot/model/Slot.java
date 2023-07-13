package com.designing.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public  class Slot {
    private final int floorNo;
    private final int slotNumber;
    private final VehicleType vehicleTypeFits;

    @Override
    public String toString(){
        return String.format("%d %d vehicle type: %s",floorNo, slotNumber, vehicleTypeFits.name() );
    }
}
