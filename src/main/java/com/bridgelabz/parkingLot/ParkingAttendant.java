package com.bridgelabz.parkingLot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class ParkingAttendant extends ParkingLot{

    void addVehicle(VehicleDetails vehicle) {
        int index = evenDistribution();
        if (parkingLot.get(index).getCarVehicle().size() < actualCapacity[index]) {
            parkingLot.get(index).getCarVehicle().add(vehicle);
            currentCapacity[index]--;
        }
    }
    /***************************************************************************************/
    /*void vehicleDataUpdate(VehicleDetails vehicle){
        int count=0;
        for(VehicleDetails v : parkingLot.get(index).getCarVehicle()) {
            if ((v.getEndTime()).compareTo(vehicle.getStartTime()) < 0) {
                parkingLot.get(index).getCarVehicle().remove(count);
                currentCapacity[index]++;
            }
            count++;
        }
    }*/
    /************************************************************************************/
    void removeVehicle(VehicleDetails vehicle){
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        int parkingSlotNumber = findParkingSlotNumber(vehicle, parkingPlotNumber);
        if(parkingSlotNumber >= 0) {
            parkingLot.get(parkingPlotNumber).getCarVehicle().remove(parkingSlotNumber);
            currentCapacity[parkingPlotNumber]++;
        }
    }

    public int findParkingSlotNumber(VehicleDetails vehicle, int parkingPlotNumber) {
        int count=0;
        for (VehicleDetails isVehicle : parkingLot.get(parkingPlotNumber).getCarVehicle()){
            if(isVehicle.equals(vehicle)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    int findParkingPlotNumber(VehicleDetails vehicle) {
        int count = 0 ;
        for(ParkingSlot p : parkingLot){
            for(VehicleDetails v : p.getCarVehicle()){
                if (v.equals(vehicle)) return count;
            }
            count++;
        }
        return -1;
    }

    /***********************************************************************************/
    private int evenDistribution(){
        ArrayList<Integer> indexFind = new ArrayList<>();
        for(ParkingSlot p : parkingLot){
            indexFind.add(p.getCarVehicle().size());
        }
        return indexFind.indexOf(Collections.min(indexFind));
    }

    private int largeVehicleParking(){
        ArrayList<Integer> indexFind = new ArrayList<>();
        for(ParkingSlot p : parkingLot){
            indexFind.add(p.getCarVehicle().size());
        }
        return indexFind.indexOf(Collections.max(indexFind));
    }

    /************************************************************************************/
    boolean isVehicleAvailable(VehicleDetails vehicle) {
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        return parkingPlotNumber >= 0;
    }
    /*************************************************************************************/
    String timeLeftToSpaceAgain(String currentTime) throws ParseException {
        int parkingLotNumber = 0;
        int parkingSlotNumber = 0;
        String endTime = parkingLot.get(parkingLotNumber).getCarVehicle().get(parkingSlotNumber).getEndTime();
        for(ParkingSlot p : parkingLot){
            for(VehicleDetails v : p.getCarVehicle()){ if(endTime.compareTo(v.getEndTime())>0) endTime = v.getEndTime();}
        }
        return DateAndTime.timeDifference(currentTime, endTime);
    }

    public long[] calculateFare(int index) throws ParseException {
        DateAndTime.timeDifference(parkingLot.get(index).getCarVehicle().get(index).getStartTime(),
                parkingLot.get(index).getCarVehicle().get(index).getEndTime());
        return DateAndTime.timeDiff;
    }

    public boolean findVehicleInParkingLot(VehicleDetails vehicle) {
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        for(VehicleDetails v : parkingLot.get(parkingPlotNumber).getCarVehicle()){
            if(v.equals(vehicle)) return true;
        }
        return false;
    }
}
