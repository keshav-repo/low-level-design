package com.designing.parkinglot.service;

import com.designing.exception.AllSlotsBookedException;
import com.designing.parkinglot.model.*;
import com.designing.parkinglot.service.impl.ParkingLotServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;

    public ParkingLotServiceTest() {
        this.parkingLotService = new ParkingLotServiceImpl();
        parkingLotService.addParkingLot("PR1234", 2, 6);
    }

    @Test
    public void test_car() {
        List<Slot> slotList = parkingLotService.getAllSlots(VehicleType.CAR);

        slotList.forEach(System.out::println);

        Car car1 = new Car("KA-01-DB-1234", "black");
        Ticket ticket1 = parkingLotService.parkVehicle(car1);
        System.out.println(ticket1.toString());

        Car car2 = new Car("KA-01-DB-1235", "white");
        Ticket ticket2 = parkingLotService.parkVehicle(car2);
        System.out.println(ticket2.toString());

        Car car3 = new Car("KA-01-DB-1236", "white");
        Ticket ticket3 = parkingLotService.parkVehicle(car3);
        System.out.println(ticket3.toString());

        // after parking three car
        parkingLotService.getAllSlots(VehicleType.CAR).forEach(System.out::println);

        CancelTicket cancelTicket = parkingLotService.unparkCar(ticket2);
        System.out.println(cancelTicket.toString());

        parkingLotService.getAllSlots(VehicleType.CAR).forEach(System.out::println);

        Car car4 = new Car("KA-01-DB-2236", "white");
        Ticket ticket4 = parkingLotService.parkVehicle(car3);
        System.out.println(ticket4.toString());

        parkingLotService.getAllSlots(VehicleType.CAR).forEach(System.out::println);
    }

    @Test()
    public void testTruck(){
        parkingLotService.getAllSlots(VehicleType.TRUCK).forEach(System.out::println);

        Truck truck = new Truck("KA-01-DB-3000", "white");
        Ticket ticket5 = parkingLotService.parkVehicle(truck);
        System.out.println(ticket5.toString());

        Truck truck2 = new Truck("KA-01-DB-3000", "white");
        Ticket ticket6 = parkingLotService.parkVehicle(truck2);
        System.out.println(ticket6.toString());

        // since all the ticket is booked it will throw exception
        Truck truck3 = new Truck("KA-01-DB-3000", "white");
       Exception exception = assertThrows(AllSlotsBookedException.class, ()->{
            parkingLotService.parkVehicle(truck3);
        });
       assertEquals("All slot booked for vehicle type TRUCK",exception.getMessage());
    }
}
