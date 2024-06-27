package com.rider.ride.dtos;

import com.rider.ride.entities.Review;
import com.rider.ride.entities.RideState;

import java.util.List;
import java.util.UUID;

public class RideDTO {
    private Integer driver;
    private Integer passenger;
    private Float boardingLocation_X;
    private Float boardingLocation_Y;
    private Float destinationLocation_X;
    private Float destinationLocation_Y;

    public Integer getDriver(){
        return this.driver;
    }

    public Integer getPassenger(){
        return this.passenger;
    }

    public Float getBoardingLocation_X(){
        return this.boardingLocation_X;
    }

    public Float getBoardingLocation_Y(){
        return this.boardingLocation_Y;
    }

    public Float getDestinationLocation_X(){
        return this.destinationLocation_X;
    }

    public Float getDestinationLocation_Y(){
        return this.destinationLocation_Y;
    }

    public void setDriver(int driver){
        this.driver = driver;
    }

    public void setPassenger(int passenger){
        this.passenger = passenger;
    }

    public void setBoardingLocation_X(float boardingLocation_X){ this.boardingLocation_X = boardingLocation_X;}

    public void setBoardingLocation_Y(float boardingLocation_Y){
        this.boardingLocation_Y = boardingLocation_Y;
    }

    public void setDestinationLocation_X(float destinationLocation_X){
        this.destinationLocation_X = destinationLocation_X;
    }

    public void setDestinationLocation_Y(float destinationLocation_Y){
        this.destinationLocation_Y = destinationLocation_Y;
    }
}
