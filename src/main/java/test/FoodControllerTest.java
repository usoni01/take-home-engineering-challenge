package test;

import main.java.FoodTruckController;
import main.java.FoodTruckService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.when;

public class FoodControllerTest {

    @InjectMocks
    FoodTruckController foodTruckController;
    @Mock
    private FoodTruckService foodTruckService;

    @Before
    public void setup() {

    }

    @Test
    public void testAddEmployee()
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("foodtruck/{{locationId}/{info}}");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
       // Mockito.when(foodTruckService.addFoodTruck(<Mockito.anyString(), Mockito.anyString()>).thenReturn(true);
        //when(foodTruckService.addFoodTruck().thenReturn(true);
    }
}
