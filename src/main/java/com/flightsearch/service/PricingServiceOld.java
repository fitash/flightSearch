package com.flightsearch.service;

import com.flightsearch.data.AirlineFeesRepository;
import com.flightsearch.domain.Airline;
import com.flightsearch.domain.Flight;
import com.flightsearch.domain.FlightRequest;
import com.flightsearch.service.exceptions.PriceCalculationException;
import com.flightsearch.service.exceptions.ServiceExceptionMessagesConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PricingServiceOld {
    private AirlineFeesRepository feesRepository;

    public void init(AirlineFeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    public Double calculatePrice(Flight flight, FlightRequest flightRequest) throws PriceCalculationException {

        if (flight==null || flightRequest==null) {
            throw new PriceCalculationException(ServiceExceptionMessagesConstants.WRONG_PARAMETERS);
        }

        Double basePrice = flight.getBasePrice();

        Double pricePerAdult = calulatePricePerAdult(basePrice, flightRequest.getDaysToDeparture());

        Double pricePerChildren = pricePerAdult * 0.67;

        Double pricePerInfant = calulatePricePerInfant(flight);

        Double total =flightRequest.getAdults() * pricePerAdult + flightRequest.getChildren() * pricePerChildren + flightRequest.getInfants() * pricePerInfant;
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
   return total;

    }

    private Double calulatePricePerInfant(Flight flight) throws PriceCalculationException {
        Airline airline = flight.obtainAirline();


        if(!this.feesRepository.getAirlineFee(airline).isPresent()){
            throw new PriceCalculationException(ServiceExceptionMessagesConstants.MISSING_FEE+ " "+ airline.getName() + "("+airline.toString()+")");
        }

        return this.feesRepository.getAirlineFee(airline).get().getFee();
    }


    Double calulatePricePerAdult(Double basePrice, Integer remainingDays) {
        Double adultPrice = basePrice;
        if (remainingDays >= 30) {
            adultPrice = basePrice * 0.80;
        } else {
            if (remainingDays <= 15 && remainingDays >= 3) {
                adultPrice = adultPrice * 1.2;
            } else if (remainingDays < 3) {
                adultPrice = adultPrice * 1.5;
            }

        }
        return adultPrice;
    }

}
