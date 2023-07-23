package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final CustomerService customerService;


    public Order createOrder(Customer customer, LocalDateTime currentTimeStamp, Address address) {
        Order orderToBeAdded = Order.builder().customer(customer).createdAt(currentTimeStamp).address(address).build();
        orderRepository.save(orderToBeAdded);
        return orderToBeAdded;
    }

    public List<OrderDto> getAllDtoOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderDto).toList();
    }
}
