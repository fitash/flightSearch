package com.flightsearch.domain;


public class Flight {
    Airport origin;
    Airport destination;
    String flightCode;
    Double basePrice;

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Flight(Airport origin, Airport destination, String flightCode, Double basePrice) {
        this.origin = origin;
        this.destination = destination;
        this.flightCode = flightCode;
        this.basePrice = basePrice;
    }

    public Airline obtainAirline() {
        String twoLetters = flightCode.substring(0, 2);
        return Airline.valueOf(twoLetters);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Flight) {
            Flight otherFlight = (Flight) obj;
            return otherFlight.getOrigin().equals(this.getOrigin()) &&
                    otherFlight.getDestination().equals(this.getDestination()) &&
                    otherFlight.getFlightCode().equals(this.getFlightCode()) &&
                    otherFlight.getBasePrice().equals(this.getBasePrice());

        }
        return false;

    }

    @Override
    public String toString() {
        return "Flight{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", flightCode='" + flightCode + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
