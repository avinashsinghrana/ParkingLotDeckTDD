package com.bridgelabz.parkingLot;

public class ParkingLotSystem {
    private ParkingLotOwner parkingLotOwner;

    public ParkingLotSystem() {
        this.parkingLotOwner = new ParkingLotOwner();
    }

    public boolean park(Object vehicle) {
        boolean isCapacity = parkingLotOwner.isCapacityFull();
        return !isCapacity;
    }

    public boolean unParked(Object vehicle) {
        return parkingLotOwner.isVehicleAvailable();
    }
}
