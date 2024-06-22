package com.rider.ride.validators;

import com.rider.ride.entities.Ride;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component("beforeCreateRideValidator")
public class RideValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Ride.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Ride t = (Ride) target;
        if (checkInputNumber(t.getDriver())) {
            errors.rejectValue("driver", "Driver.empty", "Driver Precisa ser Informado");
        }

        if (checkInputNumber(t.getPassenger())) {
            errors.rejectValue("passenger", "passenger.empty", "Passenger Precisa ser Informado");
        }

        if (checkDecimal(t.getPrice())) {
            errors.rejectValue("price", "price.empty", "Price Precisa ser Informado");
        }

        if (checkInputFloat(t.getBoardingLocation_X())) {
            errors.rejectValue("Boarding Location", "BoardingLocation_X.empty", "BoardingLocation_X Precisa ser válido");
        }

        if (checkInputFloat(t.getBoardingLocation_Y())) {
            errors.rejectValue("Boarding Location", "BoardingLocation_Y.empty", "BoardingLocation_Y Precisa ser válido");
        }

        if (checkInputFloat(t.getDestinationLocation_X())) {
            errors.rejectValue("Destination Location", "DestinationLocation_X.empty", "DestinyLocation_X Precisa ser válido");
        }

        if (checkInputFloat(t.getDestinationLocation_Y())) {
            errors.rejectValue("Destiny Location", "DestinationLocation_Y.empty", "DestinationLocation_Y Precisa ser válido");
        }

    }

    private boolean checkInputFloat(Float input) {
        return input == null || Math.abs(input) < 1e-6;
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().isEmpty());
    }

    private boolean checkInputNumber(Integer input) {
        return (input == null);
    }

    private boolean checkDecimal(Double input) {
        return (input == null);
    }
}