package com.flightsearch.domain;


public class FlightRequest {
    private Integer adults;
    private Integer infants;
    private Integer children;

    private Airport origin;
    private Airport destination;
    private Integer daysToDeparture;

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getInfants() {
        return infants;
    }

    public void setInfants(Integer infants) {
        this.infants = infants;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

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

    public Integer getDaysToDeparture() {
        return daysToDeparture;
    }

    public void setRemainingDays(Integer daysToDeparture) {
        this.daysToDeparture = daysToDeparture;
    }

    @Override
    public String toString() {
        return "FlightRequest{" +
                "adults=" + adults +
                ", infants=" + infants +
                ", children=" + children +
                ", origin=" + origin +
                ", destination=" + destination +
                ", daysToDeparture=" + daysToDeparture +
                '}';
    }
}
