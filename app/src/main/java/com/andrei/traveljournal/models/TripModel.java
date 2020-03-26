package com.andrei.traveljournal.models;

import android.widget.ImageView;

public class TripModel {
    private String tripName;
    private String destination;
    private ImageView picture;
    private float rating;
    private int price;

    public TripModel() {

    }

    public TripModel(String tripName, String destination, ImageView picture, float rating, int price) {
        this.tripName = tripName;
        this.destination = destination;
        this.picture = picture;
        this.rating = rating;
        this.price = price;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
