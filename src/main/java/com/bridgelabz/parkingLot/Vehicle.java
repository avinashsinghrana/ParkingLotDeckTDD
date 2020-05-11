package com.bridgelabz.parkingLot;

public class Vehicle {
    private Vehicle.VehicleType vehicleType;
    private String vehicleNumber;
    private String startTime;
    private String endTime;
    private ParkingSlot.DriverType driverType;
    public VehicleType.ModelType modelType;
    private Vehicle.VehicleColor vehicleColor;

    enum VehicleType{
        CAR,HEAVY_VEHICLE;
        enum ModelType{
            BMW(0),TOYOTA(1),JCB(2),CRANE(3),ROLLER(4);
            ModelType(int i) { }
        }
    }
    enum VehicleColor{
        WHITE(0),BLUE(1);
        VehicleColor(int i) { }
    }
/*------------------------------- CONSTRUCTOR ----------------------------------------------------*/

    public Vehicle(ParkingSlot.DriverType driverType, Vehicle.VehicleType vehicleType, String vehicleNumber) {
        this.driverType = driverType;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }
    public Vehicle(ParkingSlot.DriverType driverType, VehicleType vehicleType, VehicleType.ModelType modelType, VehicleColor vehicleColor, String vehicleNumber, String startTime, String endTime) {
        this.driverType = driverType;
        this.vehicleType = vehicleType;
        this.modelType = modelType;
        this.vehicleColor = vehicleColor;
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

    public VehicleColor getVehicleColor() {
        return vehicleColor;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType.ModelType getModelType() {
        return modelType;
    }

    public ParkingSlot.DriverType getDriverType() {
        return driverType;
    }
    /*----------------------------- EQUALS METHOD -------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleNumber.equals(vehicle.vehicleNumber);
    }
}
