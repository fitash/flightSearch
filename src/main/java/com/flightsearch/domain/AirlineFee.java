package com.flightsearch.domain;


public class AirlineFee {
    Airline airline;
    Double fee;

    public AirlineFee(Airline airline, Double fee) {
        this.airline=airline;
        this.fee=fee;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "AirlineFee{" +
                "airline=" + airline +
                ", fee=" + fee +
                '}';
    }
}
