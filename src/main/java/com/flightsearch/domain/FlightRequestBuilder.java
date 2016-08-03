package com.flightsearch.domain;

import com.flightsearch.domain.exceptions.DomainExceptionMessagesConstants;
import com.flightsearch.domain.exceptions.WrongFlightRequestParametersException;

public class FlightRequestBuilder {


    public static interface OriginStep {
        DestinationStep setOrigin(Airport airport) throws WrongFlightRequestParametersException;
    }

    public static interface DestinationStep {
        AdultStep setDestination(Airport airport) throws WrongFlightRequestParametersException;
    }

    public static interface AdultStep {
        ChildrenStep setAdults(int adults) throws WrongFlightRequestParametersException;
    }

    public static interface ChildrenStep {
        InfantsStep setChildren(int children) throws WrongFlightRequestParametersException;
    }

    public static interface InfantsStep {
        RemainingDaysStep setInfants(int infants) throws WrongFlightRequestParametersException;

    }

    public static interface RemainingDaysStep {
        BuildStep setRemainingDays(int remainingDays) throws WrongFlightRequestParametersException;

    }


    public static interface BuildStep {
        public FlightRequest build();
    }


    public static OriginStep newBuilder() {
        return new SteppedBuilder();
    }

    private static class SteppedBuilder implements OriginStep, DestinationStep, AdultStep, ChildrenStep, InfantsStep, RemainingDaysStep, BuildStep {
        private Airport origin;
        private Airport destination;
        private Integer adults;
        private Integer children;
        private Integer infants;
        private Integer remainingDays;


        public FlightRequest build() {
            FlightRequest flightRequest = new FlightRequest();
            flightRequest.setOrigin(this.origin);
            flightRequest.setDestination(this.destination);
            flightRequest.setRemainingDays(this.remainingDays);
            flightRequest.setAdults(adults);
            flightRequest.setChildren(children);
            flightRequest.setInfants(infants);
            return flightRequest;
        }

        @Override
        public ChildrenStep setAdults(int adults) throws WrongFlightRequestParametersException {
            if (adults < 1) {
                throw new WrongFlightRequestParametersException(DomainExceptionMessagesConstants.WRONG_ADULTS);
            }
            this.adults = adults;
            return this;
        }

        @Override
        public InfantsStep setChildren(int children) throws WrongFlightRequestParametersException {
            if (children < 0) {
                throw new WrongFlightRequestParametersException(DomainExceptionMessagesConstants.WRONG_CHILDREN);
            }
            this.children = children;
            return this;
        }

        @Override
        public AdultStep setDestination(Airport destination) throws WrongFlightRequestParametersException {
            if (this.origin.equals(destination)) {
                throw new WrongFlightRequestParametersException(DomainExceptionMessagesConstants.DIFFERENT_AIRPORTS);
            }
            this.destination = destination;
            return this;
        }

        @Override
        public RemainingDaysStep setInfants(int infants) throws WrongFlightRequestParametersException {
            if (infants < 0) {
                throw new WrongFlightRequestParametersException(DomainExceptionMessagesConstants.WRONG_INFANTS);
            }
            this.infants = infants;
            return this;
        }

        @Override
        public DestinationStep setOrigin(Airport origin) throws WrongFlightRequestParametersException {
            if(origin==null){
                throw new WrongFlightRequestParametersException(DomainExceptionMessagesConstants.WRONG_AIRPORTS);
            }
            this.origin = origin;
            return this;
        }

        @Override
        public BuildStep setRemainingDays(int remainingDays) throws WrongFlightRequestParametersException {
            if(remainingDays<0){
                throw new WrongFlightRequestParametersException(DomainExceptionMessagesConstants.WRONG_DAYS);
            }
            this.remainingDays = remainingDays;
            return this;
        }

        private SteppedBuilder() {

        }

    }

}