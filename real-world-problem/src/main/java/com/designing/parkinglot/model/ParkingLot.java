package com.designing.parkinglot.model;

import com.designing.parkinglot.exception.AllSlotsBookedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParkingLot {
    private String parkingSlotId;
    private int floorCount;
    private Queue<Slot> slotsList;
    private Queue<Slot> bookedSlot;
    private int slotCountPerFloor;
    public ParkingLot(String parkingSlotId, int floorCount, int slotCountPerFloor) {
        this.parkingSlotId = parkingSlotId;
        this.floorCount = floorCount;
        this.slotCountPerFloor = slotCountPerFloor;
        slotsList = new LinkedList<>();
        bookedSlot = new LinkedList<>();
    }
    public List<Slot> getAllSpots(VehicleType vehicleType) {
        List<Slot> res = new ArrayList<>();
        for (Slot slot : slotsList) {
            if (slot.getVehicleTypeFits().equals(vehicleType)) {
                res.add(slot);
            }
        }
        return res;
    }

    public Slot getPreferredSlot(VehicleType vehicleType) {
        List<Slot> slotList = getAllSpots(vehicleType);
        if(slotList.isEmpty())
            throw new AllSlotsBookedException(String.format("All slot booked for vehicle type %s", vehicleType.name()));
        int lowestFloor = slotList.size()+1;
        int lowestSlotNo = slotList.size()+1;
        Slot res = null;
        for (Slot slot : slotList) {
            if (lowestFloor > slot.getFloorNo()) {
                res = slot;
                lowestFloor = slot.getFloorNo();
                lowestSlotNo = slot.getSlotNumber();
            }
            if( lowestFloor == slot.getFloorNo() && lowestSlotNo > slot.getSlotNumber() ){
                res = slot;
                lowestSlotNo = slot.getSlotNumber();
            }
        }
        return res;
    }

    public void addAllSlots() {
        // add first slot for truck
        for (int i = 1; i <= floorCount; i++) {
            slotsList.add(new Slot(i, 1, VehicleType.TRUCK));
        }
        // another two slots for Motorcycle
        for (int i = 1; i <= floorCount; i++) {
            slotsList.add(new Slot(i, 2, VehicleType.MOTORCYCLE));
            slotsList.add(new Slot(i, 3, VehicleType.MOTORCYCLE));
        }
        // rest for car
        for (int i = 1; i <= floorCount; i++) {
            for (int j = 4; j <= slotCountPerFloor; j++) {
                slotsList.add(new Slot(i, j, VehicleType.CAR));
            }
        }
    }

    public void bookSlot(Slot preferredSlot){
        this.slotsList.remove(preferredSlot);
        this.bookedSlot.add(preferredSlot);
    }
    public void unbookSplot(Slot slot){
        this.bookedSlot.remove(slot);
        this.slotsList.add(slot);
    }
    // all getter
    public String getParkingSlotId() {
        return parkingSlotId;
    }
}
