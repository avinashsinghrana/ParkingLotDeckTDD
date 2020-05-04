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
            boolean isParked = parkingLotSystem.park(new Vehicle("suzuki", "MP04B2544"));
            assertTrue(isParked);
        }catch (ParkingLotException e){
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenUnParked_ShouldReturnTrue() {
     try{
         parkingLotSystem.setActualCapacity(1);
         parkingLotSystem.park(new Vehicle("suzuki","MP04B2544"));
         boolean isUnParked = parkingLotSystem.unParked(new Vehicle("suzuki", "MP04B2544"));
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
            parkingLotSystem.park(new Vehicle("yamaha", "BA05D8799"));
        }catch (ParkingLotException e){
            assertEquals("parkingLot is full",e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldReturnTrueToRedirectSecurityStaff() {
        parkingLotSystem.setActualCapacity(2);
        try{
            parkingLotSystem.park(new Vehicle("suzuki","MP06S0045"));
            parkingLotSystem.park(new Vehicle("suzuki","MP06S0045"));
            boolean canRedirectSecurityStaff = parkingLotSystem.canRedirectSecurityStaff();
            assertTrue(canRedirectSecurityStaff);
        }catch (ParkingLotException e){
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }
}