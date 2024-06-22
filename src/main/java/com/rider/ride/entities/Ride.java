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
    private Float boardingLocation_X;

    @Column(nullable = false)
    private Float boardingLocation_Y;

    @Column(nullable = false)
    private Float destinationLocation_X;

    @Column(nullable = false)
    private Float destinationLocation_Y;

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

    public List<Review> getReviews(){ return this.reviews; }

    public void setId(){
        this.id = id;
    }

    public void setDriver(){
        this.driver = driver;
    }

    public void setPassenger(){
        this.passenger = passenger;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setState(RideState state){
        this.state = state;
    }

    public void setBoardingLocation_X(){
        this.boardingLocation_X = boardingLocation_X;
    }

    public void setBoardingLocation_Y(){
        this.boardingLocation_Y = boardingLocation_Y;
    }

    public void setDestinationLocation_X(){
        this.destinationLocation_X = destinationLocation_X;
    }

    public void setDestinationLocation_Y(){
        this.destinationLocation_Y = destinationLocation_Y;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
