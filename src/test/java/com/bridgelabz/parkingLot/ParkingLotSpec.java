package com.bridgelabz.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotSpec {
    private ParkingLotSystem parkingLotSystem;
    private String date;
    private Vehicle carVehicle1 = new Vehicle(ParkingSlot.DriverType.HANDICAP, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.BMW, Vehicle.VehicleColor.WHITE, "MP04B4544", "22/03/2020 08:15:52", "22/03/2020 16:15:52");
    private Vehicle carVehicle2 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.BLUE, "MP04B9999", "22/03/2020 09:15:52", "22/03/2020 09:45:59");
    private Vehicle carVehicle3 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.WHITE, "BR09B4854", "22/03/2020 06:18:52", "22/03/2020 23:35:32");
    private Vehicle carVehicle4 = new Vehicle(ParkingSlot.DriverType.HANDICAP, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.BMW, Vehicle.VehicleColor.BLUE, "BA02P9856", "22/03/2020 11:09:36", "22/03/2020 19:45:59");
    private Vehicle carVehicle5 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.BLUE, "MP01U8985", "22/03/2020 12:23:24", "22/03/2020 16:15:52");
    private Vehicle carVehicle6 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.WHITE, "BR09C7453", "22/03/2020 09:25:52", "22/03/2020 15:35:22");
    private Vehicle carVehicle7 = new Vehicle(ParkingSlot.DriverType.HANDICAP, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.BMW, Vehicle.VehicleColor.BLUE, "BA02KL786", "22/03/2020 09:09:36", "22/03/2020 19:45:59");
    private Vehicle carVehicle8 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.CAR, Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.BLUE, "MP01P5762", "22/03/2020 11:23:24", "23/03/2020 01:09:52");
    private Vehicle heavyVehicle1 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.HEAVY_VEHICLE, Vehicle.VehicleType.ModelType.ROLLER, Vehicle.VehicleColor.WHITE, "MP0TU8985", "26/03/2020 11:23:24", "28/03/2020 17:15:52");
    private Vehicle heavyVehicle2 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.HEAVY_VEHICLE, Vehicle.VehicleType.ModelType.CRANE, Vehicle.VehicleColor.BLUE, "BR06M9885", "22/03/2020 05:06:04", "27/03/2020 13:10:00");
    private Vehicle heavyVehicle3 = new Vehicle(ParkingSlot.DriverType.NORMAL, Vehicle.VehicleType.HEAVY_VEHICLE, Vehicle.VehicleType.ModelType.JCB, Vehicle.VehicleColor.WHITE, "UP0TU8879", "26/03/2020 09:09:09", "28/03/2020 23:29:01");

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = dateFormat.format(new Date());
    }

    @Test
    void givenVehicle_whenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.createParkingLot(1, 2);
            boolean isParked = parkingLotSystem.park(carVehicle5);
            assertTrue(isParked);
        } catch (ParkingLotException e) {
            assertEquals("parkingLot is full", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.createParkingLot(1, 1);
            parkingLotSystem.park(carVehicle1);
            boolean isUnParked = parkingLotSystem.unParked(carVehicle1);
            assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            assertEquals("vehicle not parked yet", e.getMessage());
        }
    }

    @Test
    void givenVehicle_WhenParkingLotIsFull_ShouldThrowException() {
        parkingLotSystem.createParkingLot(1, 2);
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
            assertEquals(85.36, totalFare, 0.1);
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
            assertEquals(85.36, totalFare, 0.1);
        } catch (ParkingLotException | ParseException e) {
            assertEquals("vehicle not found", e.getMessage());
        }
    }

    @Test
    void givenVehicles_DirectCarToCarParkingZone_WhenCarParked_ShouldReturnTrue() {
        parkingLotSystem.createParkingLot(1, 5);

        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            boolean isAvailable = parkingLotSystem.findVehicleInParkingLot(
                    new Vehicle(ParkingSlot.DriverType.HANDICAP, Vehicle.VehicleType.CAR, "BA02P9856"));
            assertTrue(isAvailable);
        } catch (ParkingLotException e) {
            assertEquals("Vehicle not found", e.getMessage());
        }
    }

    @Test
    void givenVehicles_DirectCarToCarParkingZone_WhenCarParkedEvenDistributed_ShouldReturnTrue() {
        parkingLotSystem.createParkingLot(3, 1, 1, 3);

        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.print();
        } catch (ParkingLotException e) {
            assertEquals("Vehicle not found", e.getMessage());
        }
    }

    @Test
    void givenLargeVehicle_WhenParkedToLargeNumberFreeSpacePark_ThenReturnParkNumber() {
        parkingLotSystem.createParkingLot(3, 5, 8, 16);
        try {
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

    @Test
    void asPoliceGivenColorOfVehicle_WhenColorMatchedWithVehicle_ThenReturnParkNumberAndNumberOfWhiteCar() {
        parkingLotSystem.createParkingLot(4, 1, 2, 1, 5);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            assertArrayEquals(new int[]{0, 1, 1, 1}, parkingLotSystem.findLocationOfVehicle(Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.WHITE));
            parkingLotSystem.print();
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void asPoliceGivenColorAndModelOfVehicle_WhenColorAndModelMatchedWithVehicle_ThenReturnTheRespectiveDetail() {
        parkingLotSystem.createParkingLot(3, 2, 5, 3);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(carVehicle6);
            parkingLotSystem.park(carVehicle7);
            parkingLotSystem.park(carVehicle8);
            parkingLotSystem.setAttendantName("Ricky", "Mohan", "Bahadur");
            int[] bmw = parkingLotSystem.findLocationOfVehicle(Vehicle.VehicleType.ModelType.TOYOTA);
            List<String> vehicleNumber = parkingLotSystem.getVehicleNumber(Vehicle.VehicleType.ModelType.TOYOTA, Vehicle.VehicleColor.BLUE);
            System.out.println(Arrays.toString(bmw));
            System.out.println("vehicleNumber = " + vehicleNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void asPoliceGivenModelOfVehicle_WhenModelMatchedWithVehicle_ThenReturnTheRespectiveDetail() {
        parkingLotSystem.createParkingLot(3, 2, 5, 3);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(carVehicle6);
            parkingLotSystem.park(carVehicle7);
            parkingLotSystem.park(carVehicle8);
            parkingLotSystem.setAttendantName("Ricky", "Mohan", "Bahadur");
            int[] toyotaBlueVehicle = parkingLotSystem.findLocationOfVehicle(Vehicle.VehicleType.ModelType.BMW);
            List<String> vehicleNumber = parkingLotSystem.getVehicleNumber(Vehicle.VehicleType.ModelType.BMW);
            List<String> attendantName = parkingLotSystem.getAttendantName(Vehicle.VehicleType.ModelType.BMW);
            System.out.println(Arrays.toString(toyotaBlueVehicle));
            System.out.println("attendantName = " + attendantName);
            System.out.println("vehicleNumber = " + vehicleNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fetchTheRecord_WhenAllParkedVehicleWithin30Minute_ReturnVehicleDetail() {
        parkingLotSystem.createParkingLot(3, 2, 5, 3);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle6);
            parkingLotSystem.park(carVehicle7);
            parkingLotSystem.park(carVehicle8);
            List<String> allVehicleWithinGivenTime = parkingLotSystem.findAllVehicleWithinGivenTime("22/03/2020 09:25:59");
            System.out.println(allVehicleWithinGivenTime);
        } catch (ParkingLotException | ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findVehicle() {
        parkingLotSystem.createParkingLot(3, 2, 5, 3);
        try {
            parkingLotSystem.park(carVehicle1);
            parkingLotSystem.park(carVehicle2);
            parkingLotSystem.park(carVehicle3);
            parkingLotSystem.park(carVehicle4);
            parkingLotSystem.park(carVehicle5);
            parkingLotSystem.park(carVehicle6);
            parkingLotSystem.park(carVehicle7);
            parkingLotSystem.park(carVehicle8);
            parkingLotSystem.print();
            int count = parkingLotSystem.findParkingPlotNumber(carVehicle5);
            System.out.println("count = " + count);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

}
