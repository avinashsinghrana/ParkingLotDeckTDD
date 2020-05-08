package com.bridgelabz.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingSlot {
    enum DriverType{
        NORMAL, HANDICAP;
    }

    private List<VehicleDetails> carVehicle;

    public ParkingSlot() {
        carVehicle = new ArrayList<>();
    }
    public List<VehicleDetails> getCarVehicle() {
        return carVehicle;
    }
}