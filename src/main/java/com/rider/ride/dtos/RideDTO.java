package com.rider.ride.dtos;

public class RideDTO {
    private int driver;
    private int passenger;
    private double boardingLocation_X;
    private double boardingLocation_Y;
    private double destinationLocation_X;
    private double destinationLocation_Y;

    public int getDriver(){
        return this.driver;
    }

    public int getPassenger(){
        return this.passenger;
    }

    public Double getBoardingLocation_X(){
        return (double) this.boardingLocation_X;
    }

    public Double getBoardingLocation_Y(){
        return (double) this.boardingLocation_Y;
    }

    public Double getDestinationLocation_X(){
        return (double) this.destinationLocation_X;
    }

    public Double getDestinationLocation_Y(){
        return (double) this.destinationLocation_Y;
    }

    public void setDriver(int driver){
        this.driver = driver;
    }

    public void setPassenger(int passenger){
        this.passenger = passenger;
    }

    public void setBoardingLocation_X(double boardingLocation_X){ this.boardingLocation_X = boardingLocation_X;}

    public void setBoardingLocation_Y(double boardingLocation_Y){
        this.boardingLocation_Y = boardingLocation_Y;
    }

    public void setDestinationLocation_X(double destinationLocation_X){
        this.destinationLocation_X = destinationLocation_X;
    }

    public void setDestinationLocation_Y(double destinationLocation_Y){
        this.destinationLocation_Y = destinationLocation_Y;
    }
}
