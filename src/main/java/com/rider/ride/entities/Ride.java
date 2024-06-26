package com.rider.ride.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "driver_id", nullable = false)
    private Integer driver;

    @Column(name = "passenger_id", nullable = false)
    private Integer passenger;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideState state = RideState.WAITING_DRIVER;

    @Column(nullable = false)
    private Double boardingLocation_X;

    @Column(nullable = false)
    private Double boardingLocation_Y;

    @Column(nullable = false)
    private Double destinationLocation_X;

    @Column(nullable = false)
    private Double destinationLocation_Y;

    @OneToMany(mappedBy = "ride")
    @JsonManagedReference
    private List<Review> reviews;

    public UUID getId(){
        return this.id;
    }

    public Integer getDriver(){
        return this.driver;
    }

    public Integer getPassenger(){
        return this.passenger;
    }

    public Double getPrice(){
        return this.price;
    }

    public RideState getState(){
        return this.state;
    }

    public Double getBoardingLocation_X(){
        return this.boardingLocation_X;
    }

    public Double getBoardingLocation_Y(){
        return this.boardingLocation_Y;
    }

    public Double getDestinationLocation_X(){
        return this.destinationLocation_X;
    }

    public Double getDestinationLocation_Y(){
        return this.destinationLocation_Y;
    }

    public List<Review> getReviews(){ return this.reviews; }

    public void setId(UUID id){
        this.id = id;
    }

    public void setDriver(Integer driver){
        this.driver = driver;
    }

    public void setPassenger(Integer passenger){
        this.passenger = passenger;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setState(RideState state){
        this.state = state;
    }

    public void setBoardingLocation_X(Double boardingLocationX){
        this.boardingLocation_X = boardingLocationX;
    }

    public void setBoardingLocation_Y(Double boardingLocationY){
        this.boardingLocation_Y = boardingLocationY;
    }

    public void setDestinationLocation_X(Double destinationLocationX){
        this.destinationLocation_X = destinationLocationX;
    }

    public void setDestinationLocation_Y(Double destinationLocationY){
        this.destinationLocation_Y = destinationLocationY;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
