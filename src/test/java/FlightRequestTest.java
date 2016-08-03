import com.flightsearch.domain.Airport;
import com.flightsearch.domain.FlightRequest;
import com.flightsearch.domain.FlightRequestBuilder;
import com.flightsearch.domain.exceptions.DomainExceptionMessagesConstants;
import com.flightsearch.domain.exceptions.WrongFlightRequestParametersException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by rgonza on 2/08/16.
 */
public class FlightRequestTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testDistinctAirport() throws Exception {

        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.DIFFERENT_AIRPORTS);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.AMS)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(30).build();


    }

    @Test
    public void testNullAirport() throws Exception {
        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.WRONG_AIRPORTS);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(null).setDestination(Airport.AMS)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(30).build();


    }

    @Test
    public void testAtLeastOneAdult() throws Exception {
        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.WRONG_ADULTS);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.CDG)
                        .setAdults(0).setChildren(1).setInfants(0).setRemainingDays(30).build();
    }


    @Test
    public void testPositiveAdults() throws Exception {
        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.WRONG_ADULTS);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.CDG)
                        .setAdults(-1).setChildren(1).setInfants(0).setRemainingDays(30).build();
    }



    @Test
    public void testPositiveChildren() throws Exception {
        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.WRONG_CHILDREN);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.CDG)
                        .setAdults(1).setChildren(-1).setInfants(0).setRemainingDays(30).build();

    }


    @Test
    public void testPositiveInfants() throws Exception {
        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.WRONG_INFANTS);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.CDG)
                        .setAdults(1).setChildren(0).setInfants(-1).setRemainingDays(30).build();
    }

    @Test
    public void testPositiveRemainingDays() throws Exception {
        exception.expect(WrongFlightRequestParametersException.class);
        exception.expectMessage(DomainExceptionMessagesConstants.WRONG_DAYS);
        FlightRequest newFlightSearchRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.CDG)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(-1).build();
    }





}
