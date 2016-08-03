package com.flightsearch.domain.exceptions;

/**
 * Created by rgonza on 2/08/16.
 */
public class DomainExceptionMessagesConstants {
    public static final String DIFFERENT_AIRPORTS = "The origin and destination airports must be different";
    public static final String WRONG_AIRPORTS= "The origin and destination must be valid not null airports";
    public static final String WRONG_ADULTS = "The number of adults must be greater that one";
    public static final String WRONG_INFANTS = "The number of infants must be zero or a positive number";
    public static final String WRONG_CHILDREN = "The number of children must be zero or a positive number";
    public static final String WRONG_DAYS = "The number of days to departure must be zero or a positive number";
}
