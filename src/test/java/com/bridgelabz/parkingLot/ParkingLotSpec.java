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
    private VehicleDetails carVehicle1 = new VehicleDetails(ParkingSlot.DriverType.HANDICAP, VehicleDetails.VehicleType.CAR,
            "honda", "MP04B4544", "22/03/2020 08:15:52", "22/03/2020 16:15:52");
    private VehicleDetails carVehicle2 = new VehicleDetails(ParkingSlot.DriverType.NORMAL, VehicleDetails.VehicleType.CAR,
            "HERO", "MP04B9999", "22/03/2020 09:15:52", "22/03/2020 09:45:59");
    private VehicleDetails carVehicle3 = new VehicleDetails(ParkingSlot.DriverType.NORMAL, VehicleDetails.VehicleType.CAR,
            "YAMAHA", "BR09B4854", "22/03/2020 06:18:52", "22/03/2020 23:35:32");
    private VehicleDetails carVehicle4 = new VehicleDetails(ParkingSlot.DriverType.HANDICAP, VehicleDetails.VehicleType.CAR,
            "SCORPIO", "BA02P9856", "22/03/2020 11:09:36", "22/03/2020 19:45:59");
    private VehicleDetails carVehicle5 = new VehicleDetails(ParkingSlot.DriverType.NORMAL, VehicleDetails.VehicleType.CAR,
            "TOYOTA", "MP01U8985", "22/03/2020 12:23:24", "22/03/2020 16:15:52");
    /**************************************************** HEAVY VEHICLE DETAIL **********************************************************************/
    private VehicleDetails heavyVehicle1 = new VehicleDetails(ParkingSlot.DriverType.NORMAL, VehicleDetails.VehicleType.HEAVY_VEHICLE,
                "ROLLER", "MP0TU8985", "26/03/2020 11:23:24", "28/03/2020 17:15:52");
    private VehicleDetails heavyVehicle2 = new VehicleDetails(ParkingSlot.DriverType.NORMAL, VehicleDetails.VehicleType.HEAVY_VEHICLE,
                    "CRANE", "BR06M9885", "22/03/2020 05:06:04", "27/03/2020 13:10:00");
    private VehicleDetails heavyVehicle3 = new VehicleDetails(ParkingSlot.DriverType.NORMAL, VehicleDetails.VehicleType.HEAVY_VEHICLE,
                    "JCB", "UP0TU8879", "26/03/2020 09:09:09", "28/03/2020 23:29:01");

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = dateFormat.format(new Date());
    }

    @Test
    void givenVehicle_whenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.createParkingLot(1,2);
            boolean isParked = parkingLotSystem.park(carVehicle5);
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.createParkingLot(1,1);
            parkingLotSystem.park(carVehicle1);
            boolean isUnParked = parkingLotSystem.unParked(carVehicle1);
            assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            assertEquals("vehicle not parked yet", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_ShouldThrowException() {
        parkingLotSystem.createParkingLot(1,2);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenSameVehicleFound_ShouldThrowException() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle1);
        } catch (ParkingLotException e) {
            assertEquals("vehicle already parked", e.getMessage());
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsNotFull_DoNotRedirectSecurityStaff_ReturnTrue() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle2);
            boolean canRedirectSecurityStaff = parkingLotSystem.canRedirectSecurityStaff();
            assertTrue(canRedirectSecurityStaff);
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }


    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldReturnFalseToRedirectSecurityStaff() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
                parkingLotSystem.park(carVehicle1);
                boolean canRedirectSecurityStaff = parkingLotSystem.canRedirectSecurityStaff();
                assertTrue(canRedirectSecurityStaff);
            } catch (ParkingLotException e) {
                assertEquals("parkingLot is not full", e.getMessage());
            }
        }


    @Test
    void givenVehicles_whenParkingLotIsFull_ShouldReturn_WhenParkingLot_HasSpaceAgain() throws ParkingLotException, ParseException {
        parkingLotSystem.createParkingLot(1, 2);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle2);
            assertEquals("0 days 1 hours 30 minutes 7 seconds", parkingLotSystem.timeTakenToSpaceAgain("22/03/2020 8:15:52"));
        } catch (ParkingLotException | ParseException e) {
            e.getMessage();
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_CheckPreviousVehicleToUnPark_IfTimeOver_ReturnVehicleAdded() {
        parkingLotSystem.createParkingLot(1, 3);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            boolean isParked = parkingLotSystem.park(carVehicle5);
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.getMessage();
        }
    }

    @Test
    void givenVehicles_whenParkingLotIsNotFull_ShouldReturnThrowException() {
        parkingLotSystem.createParkingLot(1, 3);

        try {
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            assertEquals("0 days 1 hours 30 minutes 7 seconds", parkingLotSystem.timeTakenToSpaceAgain("22/03/2020 8:15:52"));
        } catch (ParkingLotException | ParseException e) {
            assertEquals("parkingLot is not full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenCategoryMatched_ParkedInCarParkingArea_ShouldReturnTrue() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
            boolean isParked = parkingLotSystem.park(carVehicle4);
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenVehicleDetail_WhenMatched_UnParkedVehicle_ShouldReturnTrue() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(carVehicle3);
            boolean isVehicleAvailable = parkingLotSystem.isVehicleAvailable(carVehicle4);
            assertTrue(isVehicleAvailable);
        } catch (ParkingLotException e) {
            e.getMessage();
        }
    }

    @Test
    void givenVehicleDetails_ToCalculateFare_WhenVehicleFound_UnParkAndReturnTotalFare() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(carVehicle5);
            double totalFare = parkingLotSystem.calculateFare(carVehicle4);
            assertEquals(85.36, totalFare,0.1);
        } catch (ParkingLotException | ParseException e) {
            e.getMessage();
        }
    }


    @Test
    void givenVehicleDetails_ToCalculateFare_WhenVehicleNotFound_ShouldThrowException() {
        parkingLotSystem.createParkingLot(1, 2);
        try {
                parkingLotSystem.park(carVehicle1);
                parkingLotSystem.park(carVehicle5);
                double totalFare = parkingLotSystem.calculateFare(carVehicle4);
                assertEquals(85.36,totalFare,0.1);
            } catch (ParkingLotException | ParseException e) {
                assertEquals("vehicle not found", e.getMessage());
            }
        }

    @Test
    void givenVehicles_DirectCarToCarParkingZone_WhenCarParked_ShouldReturnTrue() {
        parkingLotSystem.createParkingLot(1, 5);

        try{
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            boolean isAvailable = parkingLotSystem.findVehicleInParkingLot(
                    new VehicleDetails(ParkingSlot.DriverType.HANDICAP, VehicleDetails.VehicleType.CAR, "SCORPIO", "BA02P9856"));
            assertTrue(isAvailable);
        }catch (ParkingLotException e) {
            assertEquals("Vehicle not found", e.getMessage());
        }
    }

    @Test
    void givenVehicles_DirectCarToCarParkingZone_WhenCarParkedEvenDistributed_ShouldReturnTrue() {
        parkingLotSystem.createParkingLot(3,1,1,3);

        try{
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.print();
        }catch (ParkingLotException e) {
            assertEquals("Vehicle not found", e.getMessage());
        }
    }

    @Test
    void givenLargeVehicle_WhenParkedToLargeNumberFreeSpacePark_ThenReturnParkNumber() {
        parkingLotSystem.createParkingLot(3, 5,8,16);
        try{
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(heavyVehicle1);
            parkingLotSystem.park(heavyVehicle2);
            boolean isParked = parkingLotSystem.park(heavyVehicle3);
            assertTrue(isParked);
            parkingLotSystem.print();
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
