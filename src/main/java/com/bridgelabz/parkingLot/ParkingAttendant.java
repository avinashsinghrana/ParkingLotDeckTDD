package com.bridgelabz.parkingLot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParkingAttendant extends ParkingLot{
    public int parkingLotSequence;
    public String[] attendantName;
    public HashMap<String, List<ParkingSlot>> attendantProfile = new HashMap<>();

    public void setAttendantName(String... attendantName) {
        this.attendantName = attendantName;
        for (int i = 0 ; i < parkingLot.size() ; i++){
            attendantProfile.put(attendantName[i], getParkingLot());
        }
    }

    void addVehicle(Vehicle vehicle) {
        if(vehicle.getVehicleType() == Vehicle.VehicleType.CAR){
            carParkingDistribution(vehicle);
        }
        else if(vehicle.getVehicleType() == Vehicle.VehicleType.HEAVY_VEHICLE){
            largeVehicleParking(vehicle);
        }
    }

    void removeVehicle(Vehicle vehicle){
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        int parkingSlotNumber = findParkingSlotNumber(vehicle, parkingPlotNumber);
        if(parkingSlotNumber >= 0) {
            parkingLot.get(parkingPlotNumber).getCarVehicle().remove(parkingSlotNumber);
            currentCapacity[parkingPlotNumber]++;
        }
    }

    public int findParkingSlotNumber(Vehicle vehicle, int parkingPlotNumber) {
        int count=0;
        for (Vehicle isVehicle : parkingLot.get(parkingPlotNumber).getCarVehicle()){
            if(isVehicle.equals(vehicle)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    int findParkingPlotNumber(Vehicle vehicle) {
        int count = 0 ;
        for(ParkingSlot p : parkingLot){
            for(Vehicle v : p.getCarVehicle()){
                if (v.equals(vehicle)) return count;
            }
            count++;
        }
        return -1;
    }

    private void carParkingDistribution(Vehicle vehicle){
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

    private void largeVehicleParking(Vehicle vehicle){
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

    boolean isVehicleAvailable(Vehicle vehicle) {
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        return parkingPlotNumber >= 0;
    }

    String timeLeftToSpaceAgain(String currentTime) throws ParseException {
        int parkingLotNumber = 0;
        int parkingSlotNumber = 0;
        String endTime = parkingLot.get(parkingLotNumber).getCarVehicle().get(parkingSlotNumber).getEndTime();
        for(ParkingSlot p : parkingLot){
            for(Vehicle v : p.getCarVehicle()){ if(endTime.compareTo(v.getEndTime())>0) endTime = v.getEndTime();}
        }
        return DateAndTime.timeDifference(currentTime, endTime);
    }

    public long[] calculateFare(int index) throws ParseException {
        DateAndTime.timeDifference(parkingLot.get(index).getCarVehicle().get(index).getStartTime(),
                parkingLot.get(index).getCarVehicle().get(index).getEndTime());
        return DateAndTime.timeDiff;
    }

    public boolean findVehicleInParkingLot(Vehicle vehicle) {
        int parkingPlotNumber = findParkingPlotNumber(vehicle);
        for(Vehicle v : parkingLot.get(parkingPlotNumber).getCarVehicle()){
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

    public List<String> findAllVehicleWithinGivenTime(String currentTime) throws ParseException {
        List<String> vehicleNumber = new ArrayList<>();
        for(ParkingSlot p : parkingLot){
            for(Vehicle v : p.getCarVehicle()){
                DateAndTime.timeDifference(v.getStartTime(),currentTime);
                long[] time = DateAndTime.getTimeDiff();
                long parkedBefore = (time[0]*24*60)+(time[1]*60)+time[2];
                System.out.println("parkedBefore = " + parkedBefore);
                if(parkedBefore <= 30 && parkedBefore >= 0) vehicleNumber.add(v.getModelType()+" "+v.getVehicleNumber());
            }
        }
        return vehicleNumber;
    }
}
