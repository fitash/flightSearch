package com.flightsearch.services;

import com.flightsearch.data.AirlineFeesRepository;
import com.flightsearch.domain.*;
import com.flightsearch.service.PricingService;
import com.flightsearch.service.exceptions.PriceCalculationException;
import com.flightsearch.service.exceptions.ServiceExceptionMessagesConstants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by rgonza on 3/08/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PricingServiceTest {

    PricingService pricingService;

    @Mock
    AirlineFeesRepository airlineFeesRepository;



    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        this.pricingService = new PricingService();
        this.pricingService.init(airlineFeesRepository);
        //The repository just contains information about one airline
        when(airlineFeesRepository.getAirlineFee(Airline.TK)).thenReturn(Optional.of(new AirlineFee(Airline.TK, 5.)));

    }

    @Test
    public void testCaseWithInfants() throws Exception {
        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.LHR).setDestination(Airport.IST)
                        .setAdults(2).setChildren(1).setInfants(1).setRemainingDays(15).build();
        Flight flight = new Flight(Airport.LHR, Airport.AMS.IST, "TK8891", 250.);
        Double price = pricingService.calculatePrice(flight, newFlightRequest);
        assertThat(price, closeTo(806., 0.0001));
    }

    @Test
    public void testUnkownAirlineFeesCase() throws Exception {
        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.LHR).setDestination(Airport.IST)
                        .setAdults(2).setChildren(1).setInfants(1).setRemainingDays(15).build();

        exception.expect(PriceCalculationException.class);
        exception.expectMessage(ServiceExceptionMessagesConstants.MISSING_FEE);
        Flight flight = new Flight(Airport.LHR, Airport.AMS.IST, "LH1085", 148.);

       Double price = pricingService.calculatePrice(flight, newFlightRequest);

    }


    @Test
    public void testNullFlight() throws Exception {
        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.LHR).setDestination(Airport.IST)
                        .setAdults(2).setChildren(1).setInfants(1).setRemainingDays(15).build();

        exception.expect(PriceCalculationException.class);
        exception.expectMessage(ServiceExceptionMessagesConstants.WRONG_PARAMETERS);
        Flight flight = new Flight(Airport.LHR, Airport.AMS.IST, "LH1085", 148.);

        Double price = pricingService.calculatePrice(null, newFlightRequest);

    }

    @Test
    public void testNullFlightRequest() throws Exception {


        exception.expect(PriceCalculationException.class);
        exception.expectMessage(ServiceExceptionMessagesConstants.WRONG_PARAMETERS);
        Flight flight = new Flight(Airport.LHR, Airport.AMS.IST, "LH1085", 148.);

        Double price = pricingService.calculatePrice(flight, null);

    }
}
