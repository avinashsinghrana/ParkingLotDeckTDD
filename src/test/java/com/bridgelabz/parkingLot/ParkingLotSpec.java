package com.bridgelabz.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotSpec {
    private ParkingLotSystem parkingLotSystem;
    private String date;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = dateFormat.format(new Date());
    }

    @Test
    void givenVehicle_whenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.setActualCapacity(1);
            boolean isParked = parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "suzuki", "MP04B2544"));
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.setActualCapacity(1);
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "suzuki", "MP04B2544"));
            boolean isUnParked = parkingLotSystem.unParked(new VehicleDetails(VehicleDetails.VehicleType.CAR, "suzuki", "MP04B2544"));
            assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            assertEquals("vehicle not parked yet", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_ShouldThrowException() {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "honda", "BA05G8799"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "HERO", "BA0548799"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "yamaha", "BA05D8799"));
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenSameVehicleFound_ShouldThrowException() {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "hero", "MP05F0014"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "hero", "MP05F0014"));
        } catch (ParkingLotException e) {
            assertEquals("vehicle already parked", e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldReturnTrueToRedirectSecurityStaff() {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "suzuki", "MP06S0045"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "suzuki", "MP06L0045"));
            boolean canRedirectSecurityStaff = parkingLotSystem.canRedirectSecurityStaff();
            assertTrue(canRedirectSecurityStaff);
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }

    @Test
    void givenVehicles_whenParkingLotIsFull_ShouldReturn_WhenParkingLot_HasSpaceAgain() throws ParkingLotException, ParseException {
        parkingLotSystem.setActualCapacity(3);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "honda", "MP04B4544", "22/03/2020 08:15:52", "22/03/2020 16:15:52"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "HERO", "MP04B9999", "22/03/2020 09:15:52", "22/03/2020 09:45:59"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "YAMAHA", "BR09B4854", "22/03/2020 08:15:52", "22/03/2020 23:35:32"));
        } catch (ParkingLotException e) {
            assertEquals("0 days 1 hours 30 minutes 7 seconds", parkingLotSystem.timeTakenToSpaceAgain("22/03/2020 8:15:52"));
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_CheckPreviousVehicleToUnPark_IfTimeOver_ReturnVehicleAdded() {
        parkingLotSystem.setActualCapacity(3);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "honda", "MP04B4544", "22/03/2020 08:15:52", "22/03/2020 16:15:52"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "HERO", "MP04B9999", "22/03/2020 09:15:52", "22/03/2020 09:45:59"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "YAMAHA", "BR09B4854", "22/03/2020 08:15:52", "22/03/2020 23:35:32"));
            boolean isParked = parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "YAMAHA", "BR07C4854", "22/03/2020 09:46:10", "22/03/2020 23:35:32"));
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.getMessage();
        }
    }

    @Test
    void givenVehicles_whenParkingLotIsNotFull_ShouldReturnThrowException() {
        parkingLotSystem.setActualCapacity(3);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "honda", "MP04B4544", "22/03/2020 08:15:52", "22/03/2020 16:15:52"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "HERO", "MP04B9999", "22/03/2020 09:15:52", "22/03/2020 09:45:59"));
            assertEquals("0 days 1 hours 30 minutes 7 seconds", parkingLotSystem.timeTakenToSpaceAgain("22/03/2020 8:15:52"));
        } catch (ParkingLotException | ParseException e) {
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenCategoryMatched_ParkedInCarParkingArea_ShouldReturnTrue() {
        parkingLotSystem.setActualCapacity(2);
        try {
            boolean isParked = parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "SCORPIO", "BA02P9856", "22/03/2020 09:15:52", "22/03/2020 09:45:59"));
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicleDetail_WhenMatched_UnParkedVehicle_ShouldReturnTrue() {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "SCORPIO", "BA02P9856", "22/03/2020 09:15:52", "22/03/2020 09:45:59"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "TOYOTA", "MP01UT985", "22/03/2020 08:15:52", "22/03/2020 16:15:52"));
            boolean isVehicleAvailable = parkingLotSystem.isVehicleAvailable(new VehicleDetails(VehicleDetails.VehicleType.CAR, "SCORPIO", "BA02P9856"));
            assertTrue(isVehicleAvailable);
        } catch (ParkingLotException e) {
            e.getMessage();
        }
    }

    @Test
    void givenVehicleDetails_WhenVehicleFound_UnParkAndReturnTotalFare() {
        parkingLotSystem.setActualCapacity(2);
        try {
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "SCORPIO", "BA02P9856", "22/03/2020 09:15:52", "24/03/2020 09:45:59"));
            parkingLotSystem.park(new VehicleDetails(VehicleDetails.VehicleType.CAR, "TOYOTA", "MP01UT985", "22/03/2020 08:15:52", "22/03/2020 16:15:52"));
            double totalFare = parkingLotSystem.calculateFare(new VehicleDetails(VehicleDetails.VehicleType.CAR, "SCORPIO", "BA02P9856"));
            assertEquals(405.47,totalFare,0.1);
        } catch (ParkingLotException | ParseException e) {
            e.getMessage();
        }
    }

}