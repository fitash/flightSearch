package com.flightsearch.domain;

/**
 * Created by rgonza on 28/07/16.
 */
public enum Airport {
    MAD("Madrid"), BCN("Barcelona"), LHR ("London"), CDG ("Paris"),
    FRA("Frakfurt"),IST("Istanbul"), AMS("Amsterdam"),FCO("Rome"), CPH("Copenhagen");

    private String city;

    private Airport(String city) {
        this.city = city;

    }

    public String getCity() {
        return city;
    }
}
