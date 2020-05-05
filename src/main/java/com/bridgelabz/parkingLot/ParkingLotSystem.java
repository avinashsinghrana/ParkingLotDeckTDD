package com.bridgelabz.parkingLot;

import java.text.ParseException;

public class ParkingLotSystem {
    private ParkingLotObserver parkingLotObserver;
    private ParkingArea parkingArea;
    public ParkingLotSystem() {
        parkingLotObserver = new ParkingLotObserver();
    }

    public void setActualCapacity(int actualCapacity) {
        parkingLotObserver.setActualCapacity(actualCapacity);

    }

    public boolean park(VehicleDetails vehicle) throws ParkingLotException {
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

    public boolean unParked(VehicleDetails vehicle) throws ParkingLotException {
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

    public String timeTakenToSpaceAgain(String currentTime) throws ParkingLotException, ParseException {
        if(!parkingLotObserver.isCapacityNotFull())
            return parkingLotObserver.timeLeftToSpaceAgain(currentTime);
        else throw new ParkingLotException("parkingLot is not full");
    }

    public boolean isVehicleAvailable(VehicleDetails vehicle) {
        return parkingLotObserver.isVehicleAvailable(vehicle);
    }
}
