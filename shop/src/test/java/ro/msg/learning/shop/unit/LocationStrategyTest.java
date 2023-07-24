package ro.msg.learning.shop.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

@ExtendWith(MockitoExtension.class)
public class LocationStrategyTest {
    @Mock
    private LocationService locationService;

    @Mock
    private ProductService productService;

    @Mock
    private StockService stockService;

    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

    @InjectMocks
    private MostAbundantStrategy mostAbundantStrategy;


    @Test
    void testSingleLocationStrategy() {
        Address address1 = new Address("a11", "a12", "a13", "a14");
        Location location1 = new Location("l1", address1);

    }

    @Test
    void testMostAbundantStrategy() {

    }
}
