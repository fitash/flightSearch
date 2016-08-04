
[![Build Status](https://travis-ci.org/fitash/flightSearch.svg)](https://travis-ci.org/fitash/flightSearch)
# flightSearch

The main service of the application is com.flightsearch.service.FlightSearchingService.
This service exposes the method that provides the required functionality

public List<FlightResponse> searchFlights(FlightRequest flightRequest) throws FlightSearchException 

The FlightRequest object encapsulates all the needed parameters (i.e. #adults, #children, etc..) and its built using a stepped builder that assures that the object is built in a consistent state




