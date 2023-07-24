package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.key.OrderDetailKey;
import ro.msg.learning.shop.dto.OrderDetailDto;

@Component
public class OrderDetailMapper {
    public OrderDetailDto toOrderDetailDto(OrderDetail orderDetail) {
        return new OrderDetailDto(orderDetail.getProduct().getId(), orderDetail.getQuantity());
    }

    public OrderDetail toOrderDetail(OrderDetailDto orderDetailDto) {
        return new OrderDetail(OrderDetailKey.builder().orderId(null).productId(orderDetailDto.getProductId()).build(), null, null, null, orderDetailDto.getQuantity());
    }
}
