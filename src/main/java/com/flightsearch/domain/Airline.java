package com.flightsearch.domain;

public enum Airline {
    IB("Iberia"),
    BA("British Airways"),
    LH("Lufthansa"),
    FR("Ryanair"),
    VY("Vueling"),
    TK("Turkish Airlines"),
    U2("Easyjet");

    private String name;

    private Airline(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
