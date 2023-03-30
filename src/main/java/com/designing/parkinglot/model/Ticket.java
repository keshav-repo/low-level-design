package com.designing.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Ticket {
    private String id;
    private Slot slot;
    private LocalDateTime createdAt;
    private Vehicle vehicle;
    public Ticket(String parkingId, Slot slot, Vehicle vehicle) {
        this.slot = slot;
        this.id = String.format("%s_%s_%s", parkingId, slot.getFloorNo(),slot.getSlotNumber());
        this.createdAt = LocalDateTime.now();
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return String.format("Ticket ID: %s", id);
    }
}
