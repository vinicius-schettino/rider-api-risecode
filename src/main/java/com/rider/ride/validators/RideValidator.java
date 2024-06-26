package com.rider.ride.validators;

import com.rider.ride.dtos.RideDTO;

public class RideValidator {

    public static void validateRideDTO(RideDTO rideDTO) {
        if (rideDTO.getDriver() == null || rideDTO.getDriver() == 0) {
            throw new InvalidRequestException("Driver cannot be empty");
        }
        if (rideDTO.getPassenger() == null || rideDTO.getPassenger() == 0) {
            throw new InvalidRequestException("Passenger cannot be empty");
        }
        if (rideDTO.getBoardingLocation_X() == null || rideDTO.getBoardingLocation_X() == 0) {
            throw new InvalidRequestException("Boarding location X cannot be empty");
        }
        if (rideDTO.getBoardingLocation_Y() == null || rideDTO.getBoardingLocation_Y() == 0) {
            throw new InvalidRequestException("Boarding location Y cannot be empty");
        }
        if (rideDTO.getDestinationLocation_X() == null || rideDTO.getDestinationLocation_X() == 0) {
            throw new InvalidRequestException("Destination location X cannot be empty");
        }
        if (rideDTO.getDestinationLocation_Y() == null || rideDTO.getDestinationLocation_Y() == 0) {
            throw new InvalidRequestException("Destination location Y cannot be empty");
        }
    }
}