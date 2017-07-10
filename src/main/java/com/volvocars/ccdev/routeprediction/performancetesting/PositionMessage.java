package com.volvocars.ccdev.routeprediction.performancetesting;

import java.io.Serializable;

public class PositionMessage implements Serializable {
    private String vin;
    private double lon;
    private double lat;
    private long timestamp;

    public PositionMessage() {};

    public PositionMessage(String vin, double lon, double lat, long timestamp) {
        this.vin = vin;
        this.lon = lon;
        this.lat = lat;
        this.timestamp = timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
