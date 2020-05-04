package com.bridgelabz.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotObserver {
    private int actualCapacity;
    private int currentCapacity;
    private List<Vehicle> listOfVehicle = new ArrayList<>();

/*------------------------------ CONSTRUCTOR --------------------------------------*/

    public void setActualCapacity(int value) {
        this.currentCapacity = value;
        this.actualCapacity = value;
    }

/*------------------------------ VEHICLE DATA -------------------------------------*/

    void addVehicle(Vehicle vehicle){
       if(listOfVehicle.size() < actualCapacity){
           listOfVehicle.add(vehicle);
           currentCapacity--;
       }
    }

    void removeVehicle(Vehicle vehicle){
        int index = -1 ,count = 0;
        for(Vehicle isVehicle : listOfVehicle){
            if(isVehicle.equals(vehicle)){
                index = count;
            }
            count++;
        }
        if(index >= 0 ) {
            listOfVehicle.remove(index);
            currentCapacity++;
        }
    }

    boolean isCapacityFull() {
        return currentCapacity > 0 && currentCapacity <= actualCapacity;
    }

    boolean isVehicleAvailable(Vehicle vehicle) {
        int index = -1, count = 0;
        for (Vehicle isVehicle : listOfVehicle){
            if(isVehicle.equals(vehicle)) {
                index = count;
            }
            count++;
        }
        return index >= 0;
    }
}