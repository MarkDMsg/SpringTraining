package ro.msg.learning.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.ProductOrder;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderDetailMapper orderDetailMapper;

    public OrderDto toOrderDto(ProductOrder productOrder) {
        List<OrderDetailDto> orderDetailDtos = productOrder.getOrderDetailList().stream().map(orderDetailMapper::toOrderDetailDto).toList();
        return OrderDto.builder().id(productOrder.getId()).customerId(productOrder.getCustomer().getId()).createdAt(productOrder.getCreatedAt()).orderDetailDtoList(orderDetailDtos).country(productOrder.getAddress().getCountry()).city(productOrder.getAddress().getCity()).county(productOrder.getAddress().getCounty()).streetAddress(productOrder.getAddress().getStreetAddress()).customer(productOrder.getCustomer()).address(productOrder.getAddress()).build();
    }

    public ProductOrder toOrder(OrderDto orderDto) {
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailDtoList().stream().map(orderDetailMapper::toOrderDetail).toList();
        Address address = Address.builder().country(orderDto.getCountry()).county(orderDto.getCounty()).streetAddress(orderDto.getStreetAddress()).city(orderDto.getCity()).build();
        return new ProductOrder(orderDto.getCustomer(), orderDto.getCreatedAt(), address, orderDetailList);
    }
}
