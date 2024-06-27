package com.rider.ride.dtos;

import jakarta.validation.constraints.NotNull;

public class ReviewDTO {
    private String comment;
    private int rating;

    public String getComment() {
        return this.comment;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
}
