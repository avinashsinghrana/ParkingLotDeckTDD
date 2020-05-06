package com.bridgelabz.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotArea {

    enum DriverType {
        NORMAL,HANDICAP;
    }

    private List<VehicleDetails> carNormalTypeDriver = new ArrayList<>();
    private List<VehicleDetails> carHandicapTypeDriver = new ArrayList<>();

    public void addDirectCars(ParkingLotArea.DriverType vehicleType, VehicleDetails vehicle) {
        if(vehicleType == DriverType.NORMAL) this.carNormalTypeDriver.add(vehicle);
        else if(vehicleType == DriverType.HANDICAP) this.carHandicapTypeDriver.add(vehicle);
    }

    public void removeDirectCars(DriverType vehicleType, int index) {
        if(vehicleType == DriverType.NORMAL) this.carNormalTypeDriver.remove(index);
        else if(vehicleType == DriverType.HANDICAP) this.carHandicapTypeDriver.remove(index);
    }

    public boolean checkAvailabilityOfVehicle(DriverType type, VehicleDetails vehicle) {
        if(type == DriverType.HANDICAP) return checkAvailability(vehicle, carHandicapTypeDriver);
        return checkAvailability(vehicle, carNormalTypeDriver);
    }

    private boolean checkAvailability(VehicleDetails vehicle, List<VehicleDetails> details) {
        for (VehicleDetails v : details){
            return v.equals(vehicle);
        }
        return false;
    }
}