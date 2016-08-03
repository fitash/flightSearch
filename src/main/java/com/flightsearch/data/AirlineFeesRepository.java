package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;
import com.flightsearch.data.exceptions.RepositoryInitializationException;
import com.flightsearch.domain.Airline;
import com.flightsearch.domain.AirlineFee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class AirlineFeesRepository {
    Map<Airline, AirlineFee> airlineFees;

    public void init() throws RepositoryInitializationException{
        CSVReader csvReader = new CSVReader();
        String csvFilePath = CSVReader.class.getResource("/fees.csv").getPath();
        csvReader.init(new AirlineFeeParser());
        List<AirlineFee> fees = null;
        try {
            fees = csvReader.read(csvFilePath);
        } catch (CSVReadingException e) {
            throw new RepositoryInitializationException(DataExceptionMessagesConstants.REPOSITORY_INITIALIZATION,e);
        }
        this.airlineFees = fees.stream().collect(Collectors.toMap(AirlineFee::getAirline, Function.identity()));
    }


    public Optional<AirlineFee> getAirlineFee(Airline airline) {
        return Optional.ofNullable(this.airlineFees.get(airline));
    }

    public List<AirlineFee> getAirlineFees() {
        return new ArrayList<>(this.airlineFees.values());
    }

}
