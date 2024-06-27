package com.rider.ride.services;

import com.rider.ride.entities.Review;
import com.rider.ride.entities.Ride;
import com.rider.ride.entities.RideState;
import com.rider.ride.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RidesManager {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(Ride ride) {
        ride.setReviews(new ArrayList<Review>());
        ride.setId(UUID.randomUUID());
        ride.setState(RideState.WAITING_DRIVER);
        ride.setPrice(RidePrice.calculatePrice());
        return rideRepository.save(ride);
    }

    public Ride save(Ride ride){
        return rideRepository.save(ride);
    }

    public Optional<Ride> getRide(UUID id) {
        return rideRepository.findById(id);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
}

