package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;

interface Parser<T> {
   T parse(String[] line) throws CSVReadingException;
}
