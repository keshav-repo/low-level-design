package com.designing.parkinglot.service.impl;

import com.designing.parkinglot.model.*;
import com.designing.parkinglot.service.ParkingLotService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingLotServiceImpl implements ParkingLotService {

    private ParkingLot parkingLot;

    public ParkingLotServiceImpl() {

    }

    @Override
    public ParkingLot addParkingLot(String parkingSlotId, int floorCount, int slotCountPerFloor) {
        parkingLot = new ParkingLot(parkingSlotId, floorCount, slotCountPerFloor);
        parkingLot.addAllSlots();
        return parkingLot;
    }

    @Override
    public List<Slot> getAllSlots(VehicleType vehicleType) {
        return parkingLot.getAllSpots(vehicleType);
    }

    @Override
    public Ticket parkVehicle(Vehicle vehicle) {
        VehicleType vehicleType = vehicle.getVehicleType();
        String vehNumber= vehicle.getVehNumber();
        Slot slot = parkingLot.getPreferredSlot(vehicleType);
        parkingLot.bookSlot(slot);
        return new Ticket(parkingLot.getParkingSlotId(), slot, vehicle);
    }

    @Override
    public CancelTicket unparkCar(Ticket ticket) {
        Slot slot = ticket.getSlot();
        parkingLot.unbookSplot(slot);
        return new CancelTicket(ticket.getVehicle());
    }
}
