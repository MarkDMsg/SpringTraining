package ro.msg.learning.shop.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.key.OrderDetailKey;
import ro.msg.learning.shop.domain.key.StockKey;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    private Location location1, location2;

    private Product product1, product2;

    private Stock stock1, stock2, stock3;

    @BeforeAll
    void initializeTestValues() {
        stockService = mock(StockService.class);
        productService = mock(ProductService.class);

        singleLocationStrategy = new SingleLocationStrategy(stockService, productService);

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

        StockKey stockKey1 = new StockKey(location1.getId(), product1.getId());
        Stock stock1 = new Stock(stockKey1, product1, location1, 10);

        StockKey stockKey2 = new StockKey(location1.getId(), product2.getId());
        Stock stock2 = new Stock(stockKey2, product2, location1, 10);

        StockKey stockKey3 = new StockKey(location2.getId(), product2.getId());
        Stock stock3 = new Stock(stockKey3, product2, location2, 20);
    }

    @Test
    void testSingleLocationStrategy() {

        List<Location> returnedStockLocations = new ArrayList<>();
        returnedStockLocations.add(location1);
        returnedStockLocations.add(location2);
        when(stockService.getAllStockLocations()).thenReturn(returnedStockLocations);

        when(stockService.existsProductWithQuantityAtLocation(eq(location1), eq(product1.getId()), anyInt())).thenReturn(true);
        when(stockService.existsProductWithQuantityAtLocation(eq(location2), eq(product1.getId()), anyInt())).thenReturn(false);

        when(stockService.existsProductWithQuantityAtLocation(eq(location1), eq(product2.getId()), anyInt())).thenReturn(true);
        when(stockService.existsProductWithQuantityAtLocation(eq(location2), eq(product2.getId()), anyInt())).thenReturn(true);

        when(productService.getProductById(product1.getId())).thenReturn(product1);
        when(productService.getProductById(product2.getId())).thenReturn(product2);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        OrderDetailKey orderDetailKey1 = new OrderDetailKey();
        orderDetail1.setId(orderDetailKey1);
        orderDetail1.getId().setProductId(product1.getId());
        orderDetail1.setProduct(product1);
        orderDetail1.setQuantity(1);

        OrderDetail orderDetail2 = new OrderDetail();
        OrderDetailKey orderDetailKey2 = new OrderDetailKey();
        orderDetail2.setId(orderDetailKey2);
        orderDetail2.getId().setProductId(product2.getId());
        orderDetail2.setProduct(product2);
        orderDetail2.setQuantity(1);

        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);

        List<OrderDetail> result = singleLocationStrategy.getOrderDetailsByLocationStrategy(orderDetailList);
        Location location = result.get(0).getShippedFrom();
        String name = location.getName();

        Assertions.assertEquals("l1", name);
    }

    @Test
    void testMostAbundantStrategy() {

        when(productService.getProductById(product1.getId())).thenReturn(product1);
        when(productService.getProductById(product2.getId())).thenReturn(product2);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        OrderDetailKey orderDetailKey1 = new OrderDetailKey();
        orderDetail1.setId(orderDetailKey1);
        orderDetail1.getId().setProductId(product1.getId());
        orderDetail1.setProduct(product1);
        orderDetail1.setQuantity(1);

        OrderDetail orderDetail2 = new OrderDetail();
        OrderDetailKey orderDetailKey2 = new OrderDetailKey();
        orderDetail2.setId(orderDetailKey2);
        orderDetail2.getId().setProductId(product2.getId());
        orderDetail2.setProduct(product2);
        orderDetail2.setQuantity(1);

        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);

        List<Stock> stocksForProduct1 = List.of(stock1);
        List<Stock> stocksForProduct2 = Arrays.asList(stock3, stock2);
        when(stockService.findStockWithMaximumQuantityOnLocationForProduct(product1.getId())).thenReturn(stocksForProduct1);
        when(stockService.findStockWithMaximumQuantityOnLocationForProduct(product2.getId())).thenReturn(stocksForProduct2);

        when(productService.getProductById(product1.getId())).thenReturn(product1);
        when(productService.getProductById(product2.getId())).thenReturn(product2);

        List<OrderDetail> result = mostAbundantStrategy.getOrderDetailsByLocationStrategy(orderDetailList);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("l1", result.get(0).getShippedFrom().getName());
        Assertions.assertEquals("l2", result.get(1).getShippedFrom().getName()); // Most abundant stock for product2 is in location2
    }
}
