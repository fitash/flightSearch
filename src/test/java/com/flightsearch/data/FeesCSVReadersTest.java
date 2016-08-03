package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;
import com.flightsearch.domain.AirlineFee;
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
public class FeesCSVReadersTest {
    private CSVReader<AirlineFee> csvReader;
    private String csvFilePath;
    private String emptyFilePath;
    private String wrongFilepath;


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        this.csvReader = new CSVReader<>();
        this.csvFilePath = csvReader.getClass().getResource("/fees.csv").getPath();
        this.emptyFilePath = csvReader.getClass().getResource("/empty.csv").getPath();
        this.wrongFilepath = csvReader.getClass().getResource("/wrong.csv").getPath();

        this.csvReader.init(new AirlineFeeParser());
    }


    @Test
    public void readFeesCSVFileTest() throws Exception {

        List<AirlineFee> fees = csvReader.read(csvFilePath);
        assertThat(fees, hasSize(7));

    }

    @Test
    public void readEmptyCSVFileTest() throws Exception {
        List<AirlineFee> fees =  csvReader.read(emptyFilePath);
        assertThat(fees, empty());

    }

    @Test
    public void readNonExistentCSVFileTest() throws Exception {
        exception.expect(CSVReadingException.class);
        exception.expectMessage(DataExceptionMessagesConstants.WRONG_FILE);
        List<AirlineFee> fees = csvReader.read("");

    }

    @Test
    public void readWrongCSVFileTest() throws Exception {
        exception.expect(CSVReadingException.class);
        exception.expectMessage(DataExceptionMessagesConstants.WRONG_FORMAT);
        List<AirlineFee> fees = csvReader.read(wrongFilepath);

    }


}
