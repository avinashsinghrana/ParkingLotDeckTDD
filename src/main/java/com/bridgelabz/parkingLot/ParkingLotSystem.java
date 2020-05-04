package com.bridgelabz.parkingLot;

public class ParkingLotSystem {
    private ParkingLotObserver parkingLotObserver;
    private int actualCapacity;
    public ParkingLotSystem() {
        parkingLotObserver = new ParkingLotObserver();
    }

    public void setActualCapacity(int actualCapacity) {
        this.actualCapacity = actualCapacity;
        parkingLotObserver.setActualCapacity(actualCapacity);
    }

    public boolean park(Vehicle vehicle) throws ParkingLotException {
        boolean isCapacity = parkingLotObserver.isCapacityNotFull();
        if(isCapacity)
        {
            parkingLotObserver.addVehicle(vehicle);
            return true;
        }
        else throw new ParkingLotException("parkingLot is full");
    }
/*------------------------ UN-PARKED CASE ---------------------------------------*/

    public boolean unParked(Vehicle vehicle) throws ParkingLotException {
        boolean isAvailable = parkingLotObserver.isVehicleAvailable(vehicle);
        if(isAvailable){
            parkingLotObserver.removeVehicle(vehicle);
            return true;
        }
        else throw new ParkingLotException("vehicle not parked yet");
    }
/*------------------------ SECURITY REDIRECT ---------------------------------------*/

    public boolean canRedirectSecurityStaff() throws ParkingLotException {
        if(!parkingLotObserver.isCapacityNotFull()) return true;
        else throw new ParkingLotException("parkingLot is not full");
    }
}
