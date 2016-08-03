package com.flightsearch.domain;


public class FlightResponse {
    String flightCode;
    Double price;

    public FlightResponse() {
    }

    public FlightResponse(String flightCode, Double price) {
        this.flightCode = flightCode;
        this.price = price;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FlightResponse) {
            FlightResponse otherFlightResponse = (FlightResponse)obj;
            return otherFlightResponse.getPrice().equals(this.getPrice()) && this.getFlightCode().equals(otherFlightResponse.getFlightCode());
        }
        return false;
    }

    @Override
    public String toString() {
        return "FlightResponse{" +
                "flightCode='" + flightCode + '\'' +
                ", basePrice=" + price +
                '}';
    }
}
