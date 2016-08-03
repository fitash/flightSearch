package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;
import com.flightsearch.domain.Airline;
import com.flightsearch.domain.AirlineFee;


public class AirlineFeeParser implements Parser<AirlineFee> {
    public AirlineFee parse(String[] line) throws CSVReadingException {
        try {
            Airline airline = Airline.valueOf(line[0]);
            Double fee = Double.parseDouble(line[1]);

            return new AirlineFee(airline, fee);
        } catch (Exception e) {
            throw new CSVReadingException(DataExceptionMessagesConstants.WRONG_FORMAT, e);
        }
    }
}