package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.*;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.OrderWithProductsDto;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import javax.xml.crypto.dsig.Transform;
import java.time.LocalDateTime;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerService customerService;


    public Order createOrder(Customer customer, LocalDateTime currentTimeStamp,Address address) {
        Order orderToBeAdded=Order.builder().customer(customer).createdAt(currentTimeStamp).address(address).build();
        orderRepository.save(orderToBeAdded);
        return orderToBeAdded;
    }


    public List<OrderDto> getAllDtoOrders() {
        return orderRepository.findAll().stream()
                .map(element -> orderMapper.toOrderDto(element)).collect(Collectors.toList());

    }
}
