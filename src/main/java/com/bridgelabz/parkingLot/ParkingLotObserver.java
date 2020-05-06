package com.bridgelabz.parkingLot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotObserver {
    private int actualCapacity;
    private int currentCapacity;
    private int index = -1;
    private ParkingAttendant parkingAttendant = new ParkingAttendant();
    private List<VehicleDetails> listOfVehicle = new ArrayList<>();
/*------------------------------ CONSTRUCTOR --------------------------------------*/

    void setActualCapacity(int value) {
        this.currentCapacity = value;
        this.actualCapacity = value;
    }
    /*------------------------------ VEHICLE DATA -------------------------------------*/
    void addVehicle(VehicleDetails vehicle) {
        if (listOfVehicle.size() < actualCapacity) {
            listOfVehicle.add(vehicle);
            parkingAttendant.parkingCarInParkingAreaOfCar(vehicle);
            currentCapacity--;
        }
    }
/***************************************************************************************/
    void vehicleDataUpdate(VehicleDetails vehicle){
        int count=0;
        for(VehicleDetails v : listOfVehicle) {
            if ((v.getEndTime()).compareTo(vehicle.getStartTime()) < 0) {
                listOfVehicle.remove(count);
                parkingAttendant.unParkingCarInParkingAreaOfCar(vehicle, count);
                currentCapacity++;
            }
            count++;
        }
    }
/************************************************************************************/
    void removeVehicle(VehicleDetails vehicle){
        findVehicleDetails(vehicle);
        if(index >= 0) {
            listOfVehicle.remove(index);
            parkingAttendant.unParkingCarInParkingAreaOfCar(vehicle, index);
            currentCapacity++;
        }
    }
/***********************************************************************************/
    boolean isCapacityNotFull() {
        return currentCapacity>0 && currentCapacity <= actualCapacity;
    }
/************************************************************************************/
    boolean isVehicleAvailable(VehicleDetails vehicle) {
        findVehicleDetails(vehicle);
        return index >= 0;
    }
/*************************************************************************************/
    String timeLeftToSpaceAgain(String currentTime) throws ParseException {
        String endTime = listOfVehicle.get(0).getEndTime();
        for(VehicleDetails v : listOfVehicle){
            if(endTime.compareTo(v.getEndTime())>0){
                endTime = v.getEndTime();
            }
        }
        return DateAndTime.timeDifference(currentTime, endTime);
    }

    /*************************************************************************************/
    public int findVehicleDetails(VehicleDetails vehicle){
        int count=0;
        for (VehicleDetails isVehicle : listOfVehicle){
            if(isVehicle.equals(vehicle)) {
                index = count;
            }
            count++;
        }
        return index;
    }

    public long[] calculateFare(int index) throws ParseException {
        DateAndTime.timeDifference(listOfVehicle.get(index).getStartTime(),listOfVehicle.get(index).getEndTime());
        return DateAndTime.timeDiff;
    }
}