package com.flightsearch.service;

import com.flightsearch.data.FlightsRepository;
import com.flightsearch.domain.Flight;
import com.flightsearch.domain.FlightRequest;
import com.flightsearch.domain.FlightResponse;
import com.flightsearch.service.exceptions.FlightSearchException;
import com.flightsearch.service.exceptions.PriceCalculationException;
import com.flightsearch.service.exceptions.ServiceExceptionMessagesConstants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FlightSearchingSevice {
    FlightsRepository flightsDatabase;
    PricingService pricingService;

    public void init(FlightsRepository flightsRepository, PricingService pricingService) {
        this.flightsDatabase = flightsRepository;
        this.pricingService = pricingService;
    }


    public List<FlightResponse> searchFlights(FlightRequest flightRequest) throws FlightSearchException {
        if (flightRequest == null) {
            throw new FlightSearchException(ServiceExceptionMessagesConstants.WRONG_PARAMETERS);
        }
        List<FlightResponse> flightResponses =
                this.flightsDatabase.getFlights().stream().
                        filter(flight -> isCompliant(flight, flightRequest)).
                        flatMap(flight -> generateCompliantFlightResponse(flight, flightRequest)).collect(Collectors.toList());

        return flightResponses;
    }

    private boolean isCompliant(Flight flight, FlightRequest flightRequest) {
        return flight.getOrigin().equals(flightRequest.getOrigin()) && flight.getDestination().equals(flightRequest.getDestination());
    }


    private Stream<FlightResponse> generateCompliantFlightResponse(Flight flight, FlightRequest flightRequest) {
        FlightResponse flightResponse = new FlightResponse();
        flightResponse.setFlightCode(flight.getFlightCode());
        Double totalPrice = null;
        try {
            totalPrice = pricingService.calculatePrice(flight, flightRequest);
        } catch (PriceCalculationException e) {
            return Stream.empty();
        }

        flightResponse.setPrice(totalPrice);
        return Stream.of(flightResponse);
    }
}
