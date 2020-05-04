package com.bridgelabz.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotSpec {
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
            boolean isParked = parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "suzuki", "MP04B2544"));
            assertTrue(isParked);
        }catch (ParkingLotException e){
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenUnParked_ShouldReturnTrue() {
     try{
         parkingLotSystem.setActualCapacity(1);
         parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "suzuki","MP04B2544"));
         boolean isUnParked = parkingLotSystem.unParked(new Vehicle(Vehicle.VehicleType.CAR, "suzuki", "MP04B2544"));
         assertTrue(isUnParked);
     }catch (ParkingLotException e){
         assertEquals("vehicle not parked yet",e.getMessage());
     }
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_ShouldThrowException() {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "honda", "BA05G8799"));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "HERO", "BA0548799"));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "yamaha", "BA05D8799"));
        }catch (ParkingLotException e){
            assertEquals("parkingLot is full",e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenSameVehicleFound_ShouldThrowException() {
        parkingLotSystem.setActualCapacity(2);
        try{
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "hero","MP05F0014"));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "hero","MP05F0014"));
        }catch (ParkingLotException e){
            assertEquals("vehicle already parked", e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldReturnTrueToRedirectSecurityStaff() {
        parkingLotSystem.setActualCapacity(2);
        try{
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "suzuki","MP06S0045"));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "suzuki","MP06L0045"));
            boolean canRedirectSecurityStaff = parkingLotSystem.canRedirectSecurityStaff();
            assertTrue(canRedirectSecurityStaff);
        }catch (ParkingLotException e){
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }

    @Test
    void givenVehicles_whenParkingLotIsFull_ShouldReturn_WhenParkingLot_HasSpaceAgain() throws ParkingLotException {
        parkingLotSystem.setActualCapacity(3);
        parkingLotSystem.setCurrentTime(9.40);
        try{
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "honda","MP04B4544",8.15,10.45));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "HERO","MP04B9999",8.45,15.58));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "YAMAHA","BR09B4854",9.32,16.52));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "YAMAHA","BR09L8854",9.32,16.52));
        }catch (ParkingLotException e){
            assertEquals(1.04,parkingLotSystem.timeTakenToSpaceAgain(),0.1);
        }
    }

    @Test
    void givenVehicles_whenParkingLotIsNotFull_ShouldReturnThrowException() {
        parkingLotSystem.setActualCapacity(3);
        parkingLotSystem.setCurrentTime(9.40);
        try{
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "honda","MP04B4544",8.15,10.45));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR, "HERO","MP04B9999",8.45,15.58));
            assertEquals(1.04,parkingLotSystem.timeTakenToSpaceAgain(),0.1);
        }catch (ParkingLotException e){
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenCategoryMatched_ParkedInCarParkingArea_ShouldReturnTrue() {
        parkingLotSystem.setActualCapacity(2);
        try{
            boolean isParked = parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR,"SCORPIO","BA02P9856",6.15,14.25));
            assertTrue(isParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicleDetail_WhenMatched_UnParkedVehicle_ShouldReturnTrue() {
        parkingLotSystem.setActualCapacity(2);
        try{
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR,"SCORPIO","BA02P9856",6.15,14.25));
            parkingLotSystem.park(new Vehicle(Vehicle.VehicleType.CAR,"TOYOTA","MP01UT985",12.00,23.25));
            boolean isVehicleAvailable = parkingLotSystem.isVehicleAvailable(new Vehicle(Vehicle.VehicleType.CAR, "SCORPIO", "BA02P9856"));
            assertTrue(isVehicleAvailable);
        }catch (ParkingLotException e){ e.printStackTrace(); }
    }
}