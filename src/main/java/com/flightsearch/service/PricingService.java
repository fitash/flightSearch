package com.flightsearch.service;

import com.flightsearch.data.AirlineFeesRepository;
import com.flightsearch.domain.Airline;
import com.flightsearch.domain.Flight;
import com.flightsearch.domain.FlightRequest;
import com.flightsearch.service.exceptions.PriceCalculationException;
import com.flightsearch.service.exceptions.ServiceExceptionMessagesConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PricingService {
    private AirlineFeesRepository feesRepository;

    private static final BigDecimal childDiscount = new BigDecimal("0.67");
    private static final BigDecimal moreThan30Discount = new BigDecimal("0.80");
    private static final BigDecimal earlyBuyerCorrection = new BigDecimal("1.2");
    private static final BigDecimal lastMinuteBuyerCorrection = new BigDecimal("1.5");


    public void init(AirlineFeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    public Double calculatePrice(Flight flight, FlightRequest flightRequest) throws PriceCalculationException {


        if (flight == null || flightRequest == null) {
            throw new PriceCalculationException(ServiceExceptionMessagesConstants.WRONG_PARAMETERS);
        }

        BigDecimal basePrice = new BigDecimal(flight.getBasePrice());

        BigDecimal pricePerAdult = calulatePricePerAdult(basePrice, flightRequest.getDaysToDeparture());

        BigDecimal pricePerChild = pricePerAdult.multiply(childDiscount);

        BigDecimal pricePerInfant = calulatePricePerInfant(flight);

        BigDecimal priceAdults =pricePerAdult.multiply(new BigDecimal(flightRequest.getAdults()));
        BigDecimal priceChildren =       pricePerChild.multiply(new BigDecimal(flightRequest.getChildren()));

        BigDecimal priceInfants =  pricePerInfant.multiply(new BigDecimal(flightRequest.getInfants()));


        BigDecimal total = priceAdults.add(priceChildren.add(priceInfants));

        return total.setScale(2, RoundingMode.HALF_UP).doubleValue();

    }

    private BigDecimal calulatePricePerInfant(Flight flight) throws PriceCalculationException {
        Airline airline = flight.obtainAirline();


        if (!this.feesRepository.getAirlineFee(airline).isPresent()) {
            throw new PriceCalculationException(ServiceExceptionMessagesConstants.MISSING_FEE + " " + airline.getName() + "(" + airline.toString() + ")");
        }

        return new BigDecimal(this.feesRepository.getAirlineFee(airline).get().getFee());
    }


    BigDecimal calulatePricePerAdult(BigDecimal basePrice, Integer remainingDays) {


       BigDecimal adultPrice = basePrice;
        if (remainingDays >= 30) {
            adultPrice = basePrice.multiply(moreThan30Discount);
        } else {
            if (remainingDays <= 15 && remainingDays >= 3) {
                adultPrice = adultPrice.multiply(earlyBuyerCorrection);
            } else if (remainingDays < 3) {
                adultPrice = adultPrice.multiply(lastMinuteBuyerCorrection);
            }
        }
        return adultPrice;
    }

}
