package com.bridgelabz.parkingLot;

public class Vehicle {
    private VehicleType vehicleType;
    private String vehicleName;
    private String vehicleNumber;
    private double startTime;
    private double endTime;

    enum VehicleType{
        CAR;
    }
/*------------------------------- CONSTRUCTOR ----------------------------------------------------*/

    public Vehicle(VehicleType vehicleType, String vehicleName, String vehicleNumber) {
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
    }
    public Vehicle(VehicleType vehicleType, String vehicleName, String vehicleNumber, double startTime, double endTime) {
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }
/*------------------------------------ getter --------------------------------------------*/
    double getEndTime() {
        return endTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
    /*----------------------------- EQUALS METHOD -------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleName.equals(vehicle.vehicleName) &&
                vehicleNumber.equals(vehicle.vehicleNumber);
    }
}
