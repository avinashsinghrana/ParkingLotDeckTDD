package com.bridgelabz.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotSpec {
    private ParkingLotSystem parkingLotSystem;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenVehicle_whenParked_ShouldReturnTrue() {
        try{
            parkingLotSystem.setActualCapacity(1);
            parkingLotSystem.park(new Vehicle("maruti", "MP04B2544"));
            boolean isParked = parkingLotSystem.park(new Vehicle("maruti", "MP04B2544"));
            assertTrue(isParked);
        }catch (ParkingLotException e){
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenUnParked_ShouldReturnTrue() {
     try{
         parkingLotSystem.setActualCapacity(1);
         parkingLotSystem.park(new Vehicle("maruti","MP04B2544"));
         boolean isUnParked = parkingLotSystem.unParked(new Vehicle("maruti", "MP04B2544"));
         assertTrue(isUnParked);
     }catch (ParkingLotException e){
         assertEquals("vehicle not parked yet",e.getMessage());
     }
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_ShouldThrowException() throws ParkingLotException {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new Vehicle("honda", "BA05G8799"));
            parkingLotSystem.park(new Vehicle("HERO", "BA0548799"));
        }catch (ParkingLotException e){
            assertEquals("parkingLot is full",e.getMessage());
        }
    }
}