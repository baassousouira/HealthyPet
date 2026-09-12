package com.example.healthypet.data.model;


public class CurrentObservation {
    /*"pubDate": 1716045973,
            "wind": {
        "chill": 62,
                "direction": "SSE",
                "speed": 3
    },
            "atmosphere": {
        "humidity": 76,
                "visibility": 9.01,
                "pressure": 1014.2
    },
            "astronomy": {
        "sunrise": "5:56 AM",
                "sunset": "8:13 PM"
    },
            "condition": {
        "temperature": 59,
                "text": "Mostly Cloudy",
                "code": 28
    }*/

    String pubDate;
    Wind wind;
    Atmosphere atmosphere;
    Astronomy astronomy;
    Condition condition;

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
