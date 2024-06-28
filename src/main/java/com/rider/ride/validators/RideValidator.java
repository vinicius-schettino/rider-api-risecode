package com.rider.ride.validators;

import com.rider.ride.dtos.RideDTO;

import java.util.ArrayList;
import java.util.List;

public class RideValidator {

    public static void validateRideDTO(RideDTO rideDTO) {
        List<String> errorMessages = new ArrayList<>();

        if (rideDTO.getDriver() <= 0) {
            errorMessages.add("Driver cannot be empty or zero");
        }
        if (rideDTO.getPassenger() <= 0) {
            errorMessages.add("Passenger cannot be empty or zero");
        }
        if (rideDTO.getDriver() < 0) {
            errorMessages.add("Driver ID cannot be negative");
        }
        if (rideDTO.getPassenger() < 0) {
            errorMessages.add("Passenger ID cannot be negative");
        }
        if (rideDTO.getBoardingLocation_X() == null || rideDTO.getBoardingLocation_X() == 0) {
            errorMessages.add("Boarding location X cannot be empty");
        }
        if (rideDTO.getBoardingLocation_Y() == null || rideDTO.getBoardingLocation_Y() == 0) {
            errorMessages.add("Boarding location Y cannot be empty");
        }
        if (rideDTO.getDestinationLocation_X() == null || rideDTO.getDestinationLocation_X() == 0) {
            errorMessages.add("Destination location X cannot be empty");
        }
        if (rideDTO.getDestinationLocation_Y() == null || rideDTO.getDestinationLocation_Y() == 0) {
            errorMessages.add("Destination location Y cannot be empty");
        }
        if (isValidLatitude(rideDTO.getBoardingLocation_Y())) {
            errorMessages.add("Boarding location Y must be between -90 and 90");
        }
        if (isValidLongitude(rideDTO.getBoardingLocation_X())) {
            errorMessages.add("Boarding location X must be between -180 and 180");
        }
        if (isValidLatitude(rideDTO.getDestinationLocation_Y())) {
            errorMessages.add("Destination location Y must be between -90 and 90");
        }
        if (isValidLongitude(rideDTO.getDestinationLocation_X())) {
            errorMessages.add("Destination location X must be between -180 and 180");
        }
        if (!errorMessages.isEmpty()) {
            throw new InvalidRequestException(String.join(", ", errorMessages));
        }
    }

    private static boolean isValidLatitude(Double latitude) {
        return latitude < -90 || latitude > 90;
    }

    private static boolean isValidLongitude(Double longitude) {
        return longitude < -180 || longitude > 180;
    }
}