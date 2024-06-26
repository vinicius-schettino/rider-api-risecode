package com.rider.ride.controllers;

import com.rider.ride.dtos.ReviewDTO;
import com.rider.ride.dtos.RideDTO;
import com.rider.ride.entities.Review;
import com.rider.ride.entities.Ride;
import com.rider.ride.entities.RideState;
import com.rider.ride.repositories.ReviewRepository;
import com.rider.ride.services.RidesManager;
import com.rider.ride.validators.InvalidRequestException;
import com.rider.ride.validators.RideValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    private RidesManager rideService;

    @Autowired
    private ReviewRepository reviewRepository;

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
    public ResponseEntity<Ride> createRide(@RequestBody RideDTO rideDTO) throws InvalidRequestException {
        RideValidator.validateRideDTO(rideDTO);
        Ride ride = new Ride();
        ride.setBoardingLocation_X(rideDTO.getBoardingLocation_X());
        ride.setBoardingLocation_Y(rideDTO.getBoardingLocation_Y());
        ride.setDestinationLocation_X(rideDTO.getDestinationLocation_X());
        ride.setDestinationLocation_Y(rideDTO.getDestinationLocation_Y());
        ride.setDriver(rideDTO.getDriver());
        ride.setPassenger(rideDTO.getPassenger());
        Ride newRide = rideService.createRide(ride);
        return new ResponseEntity<>(newRide, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable UUID id) {
        Optional<Ride> ride = rideService.getRide(id);
        return ride.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<RideState> getRideCurrentStatus(@PathVariable UUID id) {
        Optional<Ride> ride = rideService.getRide(id);
        return ride.map(value -> ResponseEntity.ok(value.getState())).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PostMapping("/{id}/reviews/add")
    public ResponseEntity<Review> addReview(@PathVariable UUID id, @RequestBody ReviewDTO reviewDTO) {
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            Review review = new Review();

            review.setComment(reviewDTO.getComment());
            review.setRating(reviewDTO.getRating());
            review.setRide(ride);

            Review newReview = reviewRepository.save(review);

            return ResponseEntity.ok(newReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable UUID id){
        Optional<Ride> optionalRide = rideService.getRide(id);
        if (optionalRide.isPresent()){
            Ride ride = optionalRide.get();
            List<Review> rideReviews = ride.getReviews();
            return ResponseEntity.ok(rideReviews);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InvalidRequestException.class)
    private ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    record ErrorResponse(String error) {
    }
}