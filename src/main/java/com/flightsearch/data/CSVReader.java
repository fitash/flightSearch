package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReader<T> {
 Parser<T> parser;
    public void init(Parser<T> parser){
        this.parser=parser;
    }

    public List<T> read(String csvFile) throws CSVReadingException{
        List<T> parsedElements = new ArrayList<>();

        String line = "";
        String separator = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {


                String[] chunkedLine = line.split(separator);

               T parsedElement=this.parser.parse(chunkedLine);
                parsedElements.add(parsedElement);
            }

        } catch (IOException e) {
            throw new CSVReadingException(DataExceptionMessagesConstants.WRONG_FILE, e);
        }
        return parsedElements;
    }

}
