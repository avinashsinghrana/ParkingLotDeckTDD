package com.bridgelabz.parkingLot;

public class ParkingAttendant {
    private ParkingLotArea parkingLotArea = new ParkingLotArea();

    public void parkingCarInParkingAreaOfCar(VehicleDetails vehicle){
        if (vehicle.getVehicleType() == VehicleDetails.VehicleType.CAR){
            if(vehicle.getDriverType() ==  ParkingLotArea.DriverType.NORMAL)
                parkingLotArea.addDirectCars(ParkingLotArea.DriverType.NORMAL, vehicle);
            else if(vehicle.getDriverType() == ParkingLotArea.DriverType.HANDICAP)
                parkingLotArea.addDirectCars(ParkingLotArea.DriverType.HANDICAP, vehicle);
        }
    }

    public void unParkingCarInParkingAreaOfCar(VehicleDetails vehicle, int index){
        if (vehicle.getVehicleType() == VehicleDetails.VehicleType.CAR){
            if(vehicle.getDriverType() ==  ParkingLotArea.DriverType.NORMAL)
                parkingLotArea.removeDirectCars(ParkingLotArea.DriverType.NORMAL, index);
            else if(vehicle.getDriverType() == ParkingLotArea.DriverType.HANDICAP)
                parkingLotArea.removeDirectCars(ParkingLotArea.DriverType.HANDICAP, index);
        }
    }

    public boolean findVehicleInParkingLot(VehicleDetails vehicle) throws ParkingLotException {
        if (vehicle.getVehicleType() == VehicleDetails.VehicleType.CAR){
            if(vehicle.getDriverType() ==  ParkingLotArea.DriverType.NORMAL)
                return parkingLotArea.checkAvailabilityOfVehicle(ParkingLotArea.DriverType.NORMAL, vehicle);
            else if(vehicle.getDriverType() == ParkingLotArea.DriverType.HANDICAP)
                return parkingLotArea.checkAvailabilityOfVehicle(ParkingLotArea.DriverType.HANDICAP, vehicle);
        }
        throw new ParkingLotException("vehicle not found");
    }


}
