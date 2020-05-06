package com.bridgelabz.parkingLot;

import java.text.ParseException;

public class ParkingLotSystem {
    private ParkingLotObserver parkingLotObserver;
    private static final double FARE_PER_SECOND = 0.01d;
    private static final double FARE_PER_MINUTES = 0.18d;
    private static final double FARE_PER_HOURS = 9.83d;
    private static final double FARE_PER_DAYS = 200d;
    private ParkingLotArea parkingLotArea;
    public ParkingLotSystem() {
        parkingLotObserver = new ParkingLotObserver();
    }

    public void setActualCapacity(int actualCapacity) {
        parkingLotObserver.setActualCapacity(actualCapacity);

    }

    public boolean park(VehicleDetails vehicle) throws ParkingLotException {
        parkingLotObserver.vehicleDataUpdate(vehicle);
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

    public double calculateFare(VehicleDetails vehicle) throws ParseException, ParkingLotException {
        int index = parkingLotObserver.findVehicleDetails(vehicle);
        if(index >= 0){
            long[] time = parkingLotObserver.calculateFare(index);
            return time[0]*FARE_PER_DAYS+time[1]*FARE_PER_HOURS+time[2]*FARE_PER_MINUTES+time[3]*FARE_PER_SECOND;
        }
        else throw new ParkingLotException("vehicle not found");
    }
}
