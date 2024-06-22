package com.rider.ride.controllers;

import com.rider.ride.entities.Ride;
import com.rider.ride.entities.RideState;
import com.rider.ride.services.RidesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private RidesManager rideService;

    @GetMapping("/rides")
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideService.getAllRides();
        if (!rides.isEmpty()) {
            return ResponseEntity.ok(rides);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        Ride savedRide = rideService.createRide(ride);
        return ResponseEntity.created(URI.create("/ride/" + savedRide.getId())).body(savedRide);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable UUID id) {
        Optional<Ride> ride = rideService.getRide(id);
        if (ride.isPresent()) {
            return ResponseEntity.ok(ride.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<RideState> getRideCurrentStatus(@PathVariable UUID id) {
        Optional<Ride> ride = rideService.getRide(id);
        if (ride.isPresent()) {
            return ResponseEntity.ok(ride.get().getState());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status/accept")
    public ResponseEntity<Ride> acceptRide(@PathVariable UUID id) {
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setState(RideState.DRIVER_ACCEPTED);
            Ride updatedRide = rideService.save(ride);
            return ResponseEntity.ok(updatedRide);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status/pickup")
    public ResponseEntity<Ride> startRide(@PathVariable UUID id) {
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setState(RideState.ONGOING_RIDE);
            Ride updatedRide = rideService.save(ride);
            return ResponseEntity.ok(updatedRide);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status/pay")
    public ResponseEntity<Ride> requestPayment(@PathVariable UUID id) {
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setState(RideState.WAITING_PAYMENT);
            Ride updatedRide = rideService.save(ride);
            return ResponseEntity.ok(updatedRide);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status/finish")
    public ResponseEntity<Ride> finishRide(@PathVariable UUID id) {
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setState(RideState.FINISHED);
            Ride updatedRide = rideService.save(ride);
            return ResponseEntity.ok(updatedRide);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status/cancel")
    public ResponseEntity<Ride> cancelRide(@PathVariable UUID id) {
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setState(RideState.CANCELED);
            Ride updatedRide = rideService.save(ride);
            return ResponseEntity.ok(updatedRide);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
