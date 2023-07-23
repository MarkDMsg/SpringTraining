package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.dto.OrderDto;

@Component
public class OrderMapper {
    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getCustomer().getId(), order.getCreatedAt(), order.getAddress().getCountry(), order.getAddress().getCity(), order.getAddress().getCounty(), order.getAddress().getStreetAddress());
    }

    public Order toOrder(OrderDto orderDto, Customer customer, Address address) {
        return new Order(customer, orderDto.getCreatedAt(), address, null);
    }


}
