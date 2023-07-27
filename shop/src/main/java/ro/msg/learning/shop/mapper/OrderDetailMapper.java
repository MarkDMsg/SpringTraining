package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.key.OrderDetailKey;
import ro.msg.learning.shop.dto.OrderDetailDto;

@Component
public class OrderDetailMapper {
    public OrderDetailDto toOrderDetailDto(OrderDetail orderDetail) {
        return OrderDetailDto.builder().productId(orderDetail.getProduct().getId()).quantity(orderDetail.getQuantity()).productOrder(orderDetail.getProductOrder()).product(orderDetail.getProduct()).shippedFrom(orderDetail.getShippedFrom()).build();
    }

    public OrderDetail toOrderDetail(OrderDetailDto orderDetailDto) {
        return OrderDetail.builder().id(OrderDetailKey.builder().productId(orderDetailDto.getProductId()).build()).quantity(orderDetailDto.getQuantity()).productOrder(orderDetailDto.getProductOrder()).product(orderDetailDto.getProduct()).shippedFrom(orderDetailDto.getShippedFrom()).build();
    }
}
