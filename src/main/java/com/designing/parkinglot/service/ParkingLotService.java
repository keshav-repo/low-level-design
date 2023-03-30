package com.designing.parkinglot.service;

import com.designing.parkinglot.model.*;

import java.util.List;

public interface ParkingLotService {
    public ParkingLot addParkingLot(String parkingSlotId, int floorCount, int slotCountPerFloor);
    public List<Slot> getAllSlots(VehicleType vehicleType);
    public Ticket parkVehicle(Vehicle vehicle) ;
    public CancelTicket unparkCar(Ticket ticket);
}
