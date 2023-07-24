package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.key.OrderDetailKey;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.service.configuration.OrderConfig;
import ro.msg.learning.shop.service.strategy.LocationStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderDetailService {

    private final OrderMapper orderMapper;

    private final OrderDetailRepository orderDetailRepository;

    private final CustomerService customerService;

    private final OrderService orderService;

    private final ProductService productService;

    private final StockService stockService;

    private final OrderConfig orderConfig;

    public OrderDto createOrderDetails(UUID customerId, List<Pair<Product, Integer>> productsWithQuantities, String country, String city, String county, String streetAddress) {
        try {//TODO: Separate Custom Exceptions and verify if products exist
            if (!customerService.verifyCustomerExists(customerId))
                throw new RuntimeException();

            LocalDateTime currentTimeStamp = LocalDateTime.now();
            Address address = new Address(country, city, county, streetAddress);
            Customer customer = customerService.getCustomerAtId(customerId);
            Order createdOrder = orderService.createOrder(customer, currentTimeStamp, address);

            LocationStrategy locationStrategy = orderConfig.locationStrategy();
            if (locationStrategy.getClass().equals(SingleLocationStrategy.class)) {
                Location bestSingleLocation = getBestSingleLocation(productsWithQuantities);
                for (Pair<Product, Integer> productAndQuantity : productsWithQuantities) {
                    OrderDetailKey createdOrderId = OrderDetailKey.builder().orderId(createdOrder.getId()).productId(productAndQuantity.a.getId()).build();
                    OrderDetail createdOrderDetail = OrderDetail.builder().id(createdOrderId).order(createdOrder).product(productAndQuantity.a).shippedFrom(bestSingleLocation).quantity(productAndQuantity.b).build();
                    stockService.substractQuantityFromStock(productAndQuantity.a, bestSingleLocation, productAndQuantity.b);
                    orderDetailRepository.save(createdOrderDetail);
                }
            } else {
                List<Location> bestLocations = orderConfig.locationStrategy().getLocationsByStrategy(productsWithQuantities);
                int index = 0;
                for (Pair<Product, Integer> productAndQuantity : productsWithQuantities) {
                    OrderDetailKey createdOrderId = OrderDetailKey.builder().orderId(createdOrder.getId()).productId(productAndQuantity.a.getId()).build();
                    OrderDetail createdOrderDetail = OrderDetail.builder().id(createdOrderId).order(createdOrder).product(productAndQuantity.a).shippedFrom(bestLocations.get(index)).quantity(productAndQuantity.b).build();
                    stockService.substractQuantityFromStock(productAndQuantity.a, bestLocations.get(index), productAndQuantity.b);
                    orderDetailRepository.save(createdOrderDetail);
                    index++;
                }
            }
            return orderMapper.toOrderDto(createdOrder);
        } catch (RuntimeException r) {
            System.out.println(r);
            return null;
        }
    }

    private Location getBestSingleLocation(List<Pair<Product, Integer>> productsWithQuantities) {
        List<Location> bestLocations = stockService.getLocationsWithSufficientProductsQuantities(productsWithQuantities);
        if (bestLocations.isEmpty())
            throw new RuntimeException();
        return bestLocations.get(0);
    }

    public List<Product> getAllProductsFromOrderDto(OrderWithProductsDto orderWithProductsDto) {
        return orderWithProductsDto.getProductsAndQuantities().stream().map(element -> productService.getProductEntityById(element.getProductId())).toList();
    }

    public List<Integer> getAllQuantitiesFromOrderDto(OrderWithProductsDto orderWithProductsDto) {
        return orderWithProductsDto.getProductsAndQuantities().stream().map(CreateOrderDto::getQuantity).toList();
    }

    public Product getProductFromOrderDto(String productName) {
        return productService.getProductEntityByName(productName);
    }

    private boolean verifyProductsExistence(List<Product> products) {
        for (Product product : products) {
            if (!productService.verifyProductExistence(product.getId()))
                return false;
        }
        return true;
    }

}
