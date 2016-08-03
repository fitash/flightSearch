package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;
import com.flightsearch.domain.Flight;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by rgonza on 31/07/16.
 */
public class FlightsCSVReadersTest {
    private CSVReader<Flight> csvReader;
    private String csvFilePath;
    private String emptyFilePath;
    private String wrongFilepath;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        this.csvReader = new CSVReader<>();
        this.csvFilePath = csvReader.getClass().getResource("/flights.csv").getPath();
        this.emptyFilePath = csvReader.getClass().getResource("/empty.csv").getPath();
        this.wrongFilepath = csvReader.getClass().getResource("/wrong.csv").getPath();

        this.csvReader.init(new FlightParser());
    }


    @Test
    public void readFligthsCSVFileTest() throws Exception {

        List<Flight> flights = csvReader.read(csvFilePath);
        assertThat(flights, hasSize(89));

    }

    @Test
    public void readEmptyCSVFileTest() throws Exception {
        List<Flight> flights = csvReader.read(emptyFilePath);
        assertThat(flights, empty());

    }

    @Test
    public void readNonExistentCSVFileTest() throws Exception {
        exception.expect(CSVReadingException.class);
        exception.expectMessage(DataExceptionMessagesConstants.WRONG_FILE);
        List<Flight> flights = csvReader.read("");

    }

    @Test
    public void readWrongCSVFileTest() throws Exception {
        exception.expect(CSVReadingException.class);
        exception.expectMessage(DataExceptionMessagesConstants.WRONG_FORMAT);
        List<Flight> flights = csvReader.read(wrongFilepath);

    }


}
