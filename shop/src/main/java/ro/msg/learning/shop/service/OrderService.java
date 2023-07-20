package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;


    @Autowired
    OrderRepository orderRepository;



    public Order createOrder(OrderWithProductsDto order) {
        Address address=new Address(order.getCountry(),order.getCity(),order.getCounty(),order.getStreetAddress());
        Order orderToBeAdded=Order.builder().customerId(order.getCustomerId()).createdAt(order.getCreatedAt()).address(address).build();
        orderRepository.save(orderToBeAdded);
        return orderToBeAdded;
    }



}
