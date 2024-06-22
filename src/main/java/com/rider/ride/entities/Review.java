package com.rider.ride.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ratting", nullable = false)
    private Integer ratting;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Ride ride;

    public UUID getId(){
        return this.id;
    }

    public Integer getRatting(){ return this.ratting; }

    public String getComment(){ return this.comment; }

    public Ride getRide(){ return this.ride; }

    public void setRatting(){ this.ratting = ratting; }

    public void setComment(){
        this.comment = comment;
    }

    public void setRide(Ride ride){ this.ride = ride; }

}
