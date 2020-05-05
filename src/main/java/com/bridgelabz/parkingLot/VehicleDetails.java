package com.bridgelabz.parkingLot;

public class VehicleDetails {
    private VehicleDetails.VehicleType vehicleType;
    private String vehicleName;
    private String vehicleNumber;
    private String startTime;
    private String endTime;

    enum VehicleType{
        CAR;
    }
/*------------------------------- CONSTRUCTOR ----------------------------------------------------*/

    public VehicleDetails(VehicleDetails.VehicleType vehicleType, String vehicleName, String vehicleNumber) {
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
    }
    public VehicleDetails(VehicleDetails.VehicleType vehicleType, String vehicleName, String vehicleNumber, String startTime, String endTime) {
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

    public VehicleDetails.VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getStartTime() {
        return startTime;
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
