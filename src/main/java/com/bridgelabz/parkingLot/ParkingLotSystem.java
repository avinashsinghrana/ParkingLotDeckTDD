package com.bridgelabz.parkingLot;

public class ParkingLotSystem {
    private ParkingLotObserver parkingLotObserver;
    public ParkingLotSystem() {
        parkingLotObserver = new ParkingLotObserver();
    }

    public void setActualCapacity(int actualCapacity) {
        parkingLotObserver.setActualCapacity(actualCapacity);
    }

    public void setCurrentTime(double currentTime){
        parkingLotObserver.setCurrentTime(currentTime);
    }

    public boolean park(Vehicle vehicle) throws ParkingLotException {
        boolean isCapacity = parkingLotObserver.isCapacityNotFull();
        boolean isAvailable = parkingLotObserver.isVehicleAvailable(vehicle);
        if(isCapacity && !isAvailable)
        {
            parkingLotObserver.addVehicle(vehicle);
            return true;
        }
        else if(isAvailable) throw new ParkingLotException("vehicle already parked");
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

    public double timeTakenToSpaceAgain() throws ParkingLotException {
        if(!parkingLotObserver.isCapacityNotFull())
            return parkingLotObserver.timeLeftToSpaceAgain();
        else throw new ParkingLotException("parkingLot is not full");
    }
}
