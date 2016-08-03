package com.flightsearch.services;

import com.flightsearch.data.FlightsRepository;
import com.flightsearch.domain.*;
import com.flightsearch.service.FlightSearchingSevice;
import com.flightsearch.service.PricingService;
import com.flightsearch.service.exceptions.FlightSearchException;
import com.flightsearch.service.exceptions.ServiceExceptionMessagesConstants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by rgonza on 3/08/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceTest {

    FlightSearchingSevice flightSearchingSevice;

    @Mock
    FlightsRepository flightsRepository;

    @Mock
    PricingService pricingService;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    FlightRequest flightRequestA;
    FlightRequest flightRequestB;
    FlightRequest flightRequestC;
    FlightRequest flightRequestD;

    @Before
    public void init() throws Exception {


        flightRequestA =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.FRA)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(30).build();

        flightRequestB =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.LHR).setDestination(Airport.IST)
                        .setAdults(2).setChildren(1).setInfants(1).setRemainingDays(15).build();

        flightRequestC =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.BCN).setDestination(Airport.MAD)
                        .setAdults(1).setChildren(2).setInfants(0).setRemainingDays(2).build();

        flightRequestD =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.CDG).setDestination(Airport.FRA)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(30).build();


        Flight flightA = new Flight(Airport.AMS, Airport.FRA, "TK2372", 197.0);
        Flight flightB = new Flight(Airport.LHR, Airport.IST, "LH1085", 481.19);
        Flight flightC = new Flight(Airport.BCN, Airport.MAD, "IB2171", 259.0);
/*

        MAD,AMS,LH7260,191
        CDG,CPH,TK2044,186
  */

        Flight flightD = new Flight(Airport.IST, Airport.CPH, "U28059", 262.);
        Flight flightE = new Flight(Airport.MAD, Airport.AMS, "LH7260", 191.);
        Flight flightF = new Flight(Airport.CDG, Airport.CPH, "TK2044", 186.);


        List<Flight> flights = Arrays.asList(flightA, flightB, flightC, flightD, flightE, flightF);

        when(flightsRepository.getFlights()).thenReturn(flights);


        when(pricingService.calculatePrice(flightA, flightRequestA)).thenReturn(157.6);
        when(pricingService.calculatePrice(flightB, flightRequestB)).thenReturn(481.19);
        when(pricingService.calculatePrice(flightC, flightRequestC)).thenReturn(909.09);

        this.flightSearchingSevice = new FlightSearchingSevice();
        this.flightSearchingSevice.init(flightsRepository, pricingService);

    }

    @Test
    public void testNullFlightRequest() throws Exception {
        exception.expect(FlightSearchException.class);
        exception.expectMessage(ServiceExceptionMessagesConstants.WRONG_PARAMETERS);
        this.flightSearchingSevice.searchFlights(null);
    }

    //These are the basic proposed test with half of the matching flights removed to check that they are not included
    @Test
    public void exampleACaseTest() throws Exception {
        List<FlightResponse> response = flightSearchingSevice.searchFlights(flightRequestA);
        assertThat(response, contains(new FlightResponse("TK2372", 157.6)));
    }


    @Test
    public void exampleBCaseTest() throws Exception {
        List<FlightResponse> response = flightSearchingSevice.searchFlights(flightRequestB);
        assertThat(response, contains(new FlightResponse("LH1085", 481.19)));
    }

    @Test
    public void exampleCCaseTest() throws Exception {
        List<FlightResponse> response = flightSearchingSevice.searchFlights(flightRequestC);
        assertThat(response, contains(new FlightResponse("IB2171", 909.09)));
    }

    @Test
    public void exampleDCaseTest() throws Exception {
        List<FlightResponse> response = flightSearchingSevice.searchFlights(flightRequestD);
        assertThat(response, empty());
    }
}
