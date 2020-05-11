package com.bridgelabz.parkingLot;

import java.text.ParseException;
import java.util.*;

public class ParkingLotSystem  extends ParkingAttendant implements ParkingLotObserver{
    private static final double FARE_PER_SECOND = 0.01d;
    private static final double FARE_PER_MINUTES = 0.18d;
    private static final double FARE_PER_HOURS = 9.83d;
    private static final double FARE_PER_DAYS = 200d;

    public boolean park(Vehicle vehicle) throws ParkingLotException {
//        vehicleDataUpdate(vehicle);
        boolean isCapacity = isCapacityNotFull();
        boolean isAvailable = isVehicleAvailable(vehicle);
        if(isCapacity && !isAvailable)
        {
            addVehicle(vehicle);
            return true;
        }
        else if(isAvailable) throw new ParkingLotException("vehicle already parked");
        else throw new ParkingLotException("parkingLot is full");
    }
/*------------------------ UN-PARKED CASE ---------------------------------------*/

    public boolean unParked(Vehicle vehicle) throws ParkingLotException {
        boolean isAvailable = isVehicleAvailable(vehicle);
        if(isAvailable){
            removeVehicle(vehicle);
            return true;
        }
        else throw new ParkingLotException("vehicle not parked yet");
    }
/*------------------------ SECURITY REDIRECT ---------------------------------------*/

    public boolean canRedirectSecurityStaff() throws ParkingLotException {
        if(!isCapacityNotFull()) return true;
        else throw new ParkingLotException("parkingLot is not full");
    }

    public String timeTakenToSpaceAgain(String currentTime) throws ParkingLotException, ParseException {
        if(!isCapacityNotFull())
            return timeLeftToSpaceAgain(currentTime);
        else throw new ParkingLotException("parkingLot is not full");
    }

    public double calculateFare(Vehicle vehicle) throws ParseException, ParkingLotException {
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        if(parkingPlotNumber >= 0){
            int index = findParkingSlotNumber(vehicle, parkingPlotNumber);
            long[] time = calculateFare(index);
            return time[0]*FARE_PER_DAYS+time[1]*FARE_PER_HOURS+time[2]*FARE_PER_MINUTES+time[3]*FARE_PER_SECOND;
        }
        else throw new ParkingLotException("vehicle not found");
    }

    int[] findLocationOfVehicle(Vehicle.VehicleType.ModelType modelType, Vehicle.VehicleColor... vehicleColor){
        int[] locationAndNumberOfVehicles = new int[parkingLot.size()]; int index = 0;
        for (ParkingSlot p : parkingLot){
            int count = 0;
            for(Vehicle v : p.getCarVehicle()){
                if(isColorRequired(vehicleColor))
                    if(v.getVehicleColor() == vehicleColor[0] && v.getModelType() == modelType) count++;
                else if (v.getModelType() == modelType) count++;
            }
            locationAndNumberOfVehicles[index] = count;
            index++;
        }
        return locationAndNumberOfVehicles;
    }

    @Override
    public boolean isCapacityNotFull() {
        int totalActualCapacity = Arrays.stream(actualCapacity).sum();
        int totalCurrentCapacity = Arrays.stream(currentCapacity).sum();
        return totalCurrentCapacity <= totalActualCapacity && totalCurrentCapacity > 0;
    }

    public List<String> getVehicleNumber(Vehicle.VehicleType.ModelType modelType, Vehicle.VehicleColor... color) {
        List<String> vehicleNumber = new ArrayList<>();
        for(ParkingSlot p : parkingLot){
            for(Vehicle v : p.getCarVehicle()){
                if(isColorRequired(color)){
                    if(v.getModelType() == modelType && v.getVehicleColor() == color[0])
                        vehicleNumber.add(v.getVehicleNumber());
                }
                else if(v.getModelType() == modelType ) vehicleNumber.add(v.getVehicleNumber());
            }
        }
        return vehicleNumber;
    }

    private boolean isColorRequired(Vehicle.VehicleColor[] color) {
        for (Vehicle.VehicleColor vColor : color)
            if (vColor != null) return true;
        return false;
    }

    public List<String> getAttendantName(Vehicle.VehicleType.ModelType modelType, Vehicle.VehicleColor... color) {
        List<String> attendant = new ArrayList<>();
        int i = 0;
        Set<String> attendantName = attendantProfile.keySet();
        List<String> temp = new ArrayList<>(attendantName);
        for(ParkingSlot p : parkingLot){
            List<Vehicle> v = p.getCarVehicle();
            if(isColorRequired(color)){
                if(v.get(i).getModelType() == modelType && v.get(i).getVehicleColor() == color[0] )
                    attendant.add(temp.get(i));
            }else if(v.get(i).getModelType() == modelType)
                    attendant.add(temp.get(i));
            i++;
        }
        return attendant;
    }

    public boolean isFraudVehicleParked(Vehicle vehicle) {
        return isVehicleAvailable(vehicle);
    }
}
