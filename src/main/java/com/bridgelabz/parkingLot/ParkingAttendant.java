package com.bridgelabz.parkingLot;

import java.text.ParseException;
import java.util.Arrays;

public class ParkingAttendant extends ParkingLot{
    public int parkingLotSequence;

    void addVehicle(VehicleDetails vehicle) {
        if(vehicle.getVehicleType() == VehicleDetails.VehicleType.CAR){
            carParkingDistribution(vehicle);
        }
        else if(vehicle.getVehicleType() == VehicleDetails.VehicleType.HEAVY_VEHICLE){
            largeVehicleParking(vehicle);
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
    }
    /***************************************************************************************/
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

    private void carParkingDistribution(VehicleDetails vehicle){
        for(int i = parkingLotSequence ; i < parkingLot.size() ; i++){
            if(currentCapacity[i] == 0){
                parkingLotSequence++;
            }else break;
        }
        if (parkingLot.get(parkingLotSequence).getCarVehicle().size() < actualCapacity[parkingLotSequence] && currentCapacity[parkingLotSequence] > 0) {
            parkingLot.get(parkingLotSequence).getCarVehicle().add(vehicle);
            currentCapacity[parkingLotSequence]--;
        }
        parkingLotSequence++;
        if(parkingLotSequence >= parkingLot.size()) parkingLotSequence = 0;
    }

    private void largeVehicleParking(VehicleDetails vehicle){
        int maxSize = currentCapacity[0];
        int parkingLotNumber = 0, count=0;
        for(int size : currentCapacity){
            if(maxSize < size) {
                maxSize = size;
                parkingLotNumber = count;
            }
            count++;
        }
        if(currentCapacity[parkingLotNumber] <= actualCapacity[parkingLotNumber] && currentCapacity[parkingLotNumber]>0){
            parkingLot.get(parkingLotNumber).getCarVehicle().add(vehicle);
            currentCapacity[parkingLotNumber]--;
        }
    }

    boolean isVehicleAvailable(VehicleDetails vehicle) {
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        return parkingPlotNumber >= 0;
    }

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
    void print(){
        for (ParkingSlot p : parkingLot){
            System.out.println("size of parking lot " + p.getCarVehicle().size());
            System.out.println("currentCapacity = " + Arrays.toString(currentCapacity));
        }
    }
}
