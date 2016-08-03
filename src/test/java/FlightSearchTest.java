import com.flightsearch.data.AirlineFeesRepository;
import com.flightsearch.data.FlightsRepository;
import com.flightsearch.domain.Airport;
import com.flightsearch.domain.FlightRequest;
import com.flightsearch.domain.FlightRequestBuilder;
import com.flightsearch.domain.FlightResponse;
import com.flightsearch.service.FlightSearchingSevice;
import com.flightsearch.service.PricingService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;


public class FlightSearchTest {

    private FlightSearchingSevice flightSearchingSevice;

    @Before
    public void init() throws Exception {

        FlightsRepository flightsRepository=new FlightsRepository();
        flightsRepository.init();

        PricingService pricingService = new PricingService();
        AirlineFeesRepository feesRepository = new AirlineFeesRepository();
        feesRepository.init();
        pricingService.init(feesRepository);


        this.flightSearchingSevice = new FlightSearchingSevice();
        this.flightSearchingSevice.init(flightsRepository,pricingService);

    }

    @Test
    public void exampleACaseTest() throws Exception {

        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.AMS).setDestination(Airport.FRA)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(30).build();

        List<FlightResponse> response = flightSearchingSevice.searchFlights(newFlightRequest);
        assertThat("Test of the first proposed example failed",response, containsInAnyOrder(new FlightResponse("TK2372", 157.6), new FlightResponse("TK2659", 198.4), new FlightResponse("LH5909", 90.4)));
    }


    @Test
    public void exampleBCaseTest() throws Exception{

        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.LHR).setDestination(Airport.IST)
                        .setAdults(2).setChildren(1).setInfants(1).setRemainingDays(15).build();

        List<FlightResponse> response = flightSearchingSevice.searchFlights(newFlightRequest);
        assertThat("Test of the second proposed example failed",response, containsInAnyOrder(new FlightResponse("TK8891", 806.), new FlightResponse("LH1085", 481.19)));
    }

    @Test
    public void exampleCCaseTest() throws Exception{

        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.BCN).setDestination(Airport.MAD)
                        .setAdults(1).setChildren(2).setInfants(0).setRemainingDays(2).build();

        List<FlightResponse> response = flightSearchingSevice.searchFlights(newFlightRequest);

        assertThat("Test of the third proposed example failed",response, containsInAnyOrder(new FlightResponse("IB2171", 909.09), new FlightResponse("LH5496", 1028.43)));
    }

    @Test
    public void exampleDCaseTest() throws Exception{


        FlightRequest newFlightRequest =
                FlightRequestBuilder.newBuilder().setOrigin(Airport.CDG).setDestination(Airport.FRA)
                        .setAdults(1).setChildren(0).setInfants(0).setRemainingDays(30).build();
        List<FlightResponse> response = flightSearchingSevice.searchFlights(newFlightRequest);
        assertThat("Test of the fourth proposed example failed",response, empty());
    }


}
