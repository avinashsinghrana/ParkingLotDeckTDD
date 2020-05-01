package com.bridgelabz.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingLotSpec {

    private ParkingLotSystem parkingLotSystem;
    private Object vehicle;

    @BeforeEach
    void setUp(){
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

   @Test
   void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
       boolean isParked = parkingLotSystem.park(vehicle);
       assertTrue(isParked);
   }

   @Test
    void givenVehicle_whenUnParked_shouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        assertTrue(isUnParked);
   }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.park(vehicle);
        assertFalse(isParked);
    }

    @Test
    void givenNoVehicle_ParkedShouldReturnException(){
        try{
            parkingLotSystem.park(null);
        }catch (ParkingLotException e){
            assertEquals("vehicle not found",e.getMessage());
        }
    }
}
