package com.bridgelabz.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotSpec {
    private ParkingLotSystem parkingLotSystem       ;
    private Object vehicle;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    void givenVehicle_whenParked_ShouldReturnTrue() {
        boolean isParked = parkingLotSystem.park(vehicle);
        assertTrue(isParked);
    }

    @Test
    void givenVehicle_WhenUpParked_ShouldReturnTrue() {
        boolean isUnParked = parkingLotSystem.unParked(vehicle);
        assertTrue(isUnParked);
    }
}
