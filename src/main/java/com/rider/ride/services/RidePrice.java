package com.rider.ride.services;

import org.springframework.stereotype.Service;

@Service
public class RidePrice {
    public static Double calculatePrice() {
        double randomPrice = Math.random() * 50;

        randomPrice = Math.round(randomPrice * 100.0) / 100.0;

        return randomPrice;
    }
}
