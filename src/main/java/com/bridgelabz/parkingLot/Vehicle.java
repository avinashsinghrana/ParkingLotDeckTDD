package com.bridgelabz.parkingLot;

public class Vehicle {
    private String vehicleType;
    private String vehicleNumber;
    private double startTime;
    private double endTime;

    public Vehicle(String vehicleType, String vehicleNumber) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }

    public Vehicle(String vehicleType, String vehicleNumber, double startTime, double endTime) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public double getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleType.equals(vehicle.vehicleType) &&
                vehicleNumber.equals(vehicle.vehicleNumber);
    }
}
