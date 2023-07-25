package ro.msg.learning.shop.unit;

import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        location1.setId(UUID.randomUUID());
        Location location2 = new Location("l2", address1);
        location2.setId(UUID.randomUUID());

        ProductCategory productCategory1 = new ProductCategory("basic", "d1");
        productCategory1.setId(UUID.randomUUID());

        Product product1 = new Product("p1", "d1", BigDecimal.ONE, 1.0, productCategory1, "supplier1", "imageUrl");
        product1.setId(UUID.randomUUID());
        Product product2 = new Product("p2", "d2", BigDecimal.ONE, 1.0, productCategory1, "supplier1", "imageUrl");
        product2.setId(UUID.randomUUID());

        List<Pair<Product, Integer>> productAndQuantityList = new ArrayList<>();
        productAndQuantityList.add(new Pair<>(product1, 1));
        productAndQuantityList.add(new Pair<>(product2, 1));

        List<Location> correctLocations = new ArrayList<>();
        correctLocations.add(location1);

//        when(stockService.getLocationsWithSufficientProductsQuantities(productAndQuantityList)).thenReturn(correctLocations);
//
//        assertThat(singleLocationStrategy.getLocationsByStrategy(productAndQuantityList)).isEqualTo(correctLocations);
    }

    @Test
    void testMostAbundantStrategy() {
        Address address1 = new Address("a11", "a12", "a13", "a14");
        Location location1 = new Location("l1", address1);
        location1.setId(UUID.randomUUID());
        Location location2 = new Location("l2", address1);
        location2.setId(UUID.randomUUID());

        ProductCategory productCategory1 = new ProductCategory("basic", "d1");
        productCategory1.setId(UUID.randomUUID());

        Product product1 = new Product("p1", "d1", BigDecimal.ONE, 1.0, productCategory1, "supplier1", "imageUrl");
        product1.setId(UUID.randomUUID());
        Product product2 = new Product("p2", "d2", BigDecimal.ONE, 1.0, productCategory1, "supplier1", "imageUrl");
        product2.setId(UUID.randomUUID());

        List<Pair<Product, Integer>> productAndQuantityList = new ArrayList<>();
        productAndQuantityList.add(new Pair<>(product1, 1));
        productAndQuantityList.add(new Pair<>(product1, 1));

        List<Location> correctLocations = new ArrayList<>();
        correctLocations.add(location1);
        correctLocations.add(location2);

//        when(stockService.getLocationsMostAbundantQuantityForProduct(productAndQuantityList)).thenReturn(correctLocations);
//
//        assertThat(mostAbundantStrategy.getLocationsByStrategy(productAndQuantityList)).isEqualTo(correctLocations);
    }
}
