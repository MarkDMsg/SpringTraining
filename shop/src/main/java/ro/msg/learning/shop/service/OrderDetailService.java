package ro.msg.learning.shop.service;

import org.antlr.v4.runtime.misc.Pair;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisAnnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.domain.Keys.OrderDetailKey;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.StockRepository;

import javax.management.RuntimeErrorException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;



import java.util.stream.Collectors;
@Service
public class OrderDetailService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    StockService stockService;

    @Autowired
    OrderService orderService;
    public OrderWithProductsDto createOrderDetail(UUID customerId, List<Pair<Product,Integer>> productsWithQuantities,String country,String city,String county,String streetAddress){
        try{//TODO: Separate Custom Exceptions and verify if products exist
            if(!customerService.verifyCustomerExists(customerId))
                throw new RuntimeException();

            Location bestSingleLocation=getBestSingleLocation(productsWithQuantities);
            LocalDateTime currentTimeStamp=LocalDateTime.now();
            Address address=new Address(country,city,county,streetAddress);

            List<Product> addedProducts=productsWithQuantities.stream().map(e->e.a).toList();
            List<Integer> addedQuantities=productsWithQuantities.stream().map(e->e.b).toList();
            Order createdOrder=new Order(customerId,currentTimeStamp,address);
            OrderWithProductsDto orderWithProductsDto=orderMapper.toOrderWithProductsDto(createdOrder,addedProducts,addedQuantities);

            List<UUID> addedProductsIds=productsWithQuantities.stream().map(e->e.a.getId()).toList();

            OrderDetailKey createdOrderId= OrderDetailKey.builder().order(createdOrder.getId()).products(addedProductsIds).build();
            OrderDetail createdOrderDetail=OrderDetail.builder().id(createdOrderId).order(createdOrder).products(addedProducts).shippedFrom(bestSingleLocation).quantities(addedQuantities).build();
            orderDetailRepository.save(createdOrderDetail);

            return orderWithProductsDto;
        }catch (RuntimeException r){
            System.out.println(r.toString());
            return null;
        }
    }

    private Location getBestSingleLocation(List<Pair<Product,Integer>> productsWithQuantities){
        List<Location> bestLocations=stockService.getLocationsWithSufficientProductsQuantities(productsWithQuantities);
        if(bestLocations.size()==0)
            throw new RuntimeException();
        return bestLocations.get(0);
    }
    private boolean verifyProductsExistence(List<Product> products){
        for (Product product : products) {
            if(!productService.verifyProductExistence(product.getId()))
                return false;
        }
        return true;
    }

}
