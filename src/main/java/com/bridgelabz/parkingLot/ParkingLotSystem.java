package com.bridgelabz.parkingLot;

public class ParkingLotSystem {
    private Object vehicle = null;

    public ParkingLotSystem(Object vehicle) {
        this.vehicle = vehicle;
    }
    public ParkingLotSystem() {
    }

    public boolean park(Object vehicle) throws ParkingLotException {
        if (vehicle == null) throw new ParkingLotException("vehicle not found");
        if (this.vehicle == (vehicle)){
            return false;
        }
        this.vehicle = vehicle ;
        return true;
    }

    public boolean unPark(Object vehicle) {
        if(this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        if (vehicle == null) return false;
        return false;
    }
}
