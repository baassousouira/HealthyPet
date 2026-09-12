package com.example.healthypet.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    /*"city": "Sunnyvale",
            "region": "California",
            "woeid": 2502265,
            "country": "United States",
            "lat": 37.371609,
            "long": -122.038254,
            "timezone_id": "America/Los_Angeles"*/
    String city;
    String region;
    Integer woeid;
    String country;
    Double lat;
    @JsonProperty("long")
    Double lon;
    @JsonProperty("timezone_id")
    String timeZoneId;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
   }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
}
