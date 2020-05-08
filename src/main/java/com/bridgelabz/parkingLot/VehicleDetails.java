package com.bridgelabz.parkingLot;

public class VehicleDetails {
    private VehicleDetails.VehicleType vehicleType;
    private String vehicleName;
    private String vehicleNumber;
    private String startTime;
    private String endTime;
    private ParkingSlot.DriverType driverType;

    enum VehicleType{
        CAR;
    }
/*------------------------------- CONSTRUCTOR ----------------------------------------------------*/

    public VehicleDetails(ParkingSlot.DriverType driverType, VehicleDetails.VehicleType vehicleType, String vehicleName, String vehicleNumber) {
        this.driverType = driverType;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleDetails(ParkingSlot.DriverType driverType, VehicleType vehicleType, String vehicleName, String vehicleNumber, String startTime, String endTime) {
        this.driverType = driverType;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /*------------------------------------ getter --------------------------------------------*/
    String getEndTime() {
        return endTime;
    }

    String getStartTime() {
        return startTime;
    }

    VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingSlot.DriverType getDriverType() {
        return driverType;
    }
    /*----------------------------- EQUALS METHOD -------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof VehicleDetails)) return false;
        VehicleDetails vehicle = (VehicleDetails) o;
        return vehicleName.equals(vehicle.vehicleName) &&
                vehicleNumber.equals(vehicle.vehicleNumber);
    }
}
