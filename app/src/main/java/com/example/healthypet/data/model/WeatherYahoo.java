package com.example.healthypet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherYahoo {
    Location location;
    @JsonProperty("current_observation")
    CurrentObservation currentObservation;
    List<Forecasts> forecasts;


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<Forecasts> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecasts> forecasts) {
        this.forecasts = forecasts;
    }
}
