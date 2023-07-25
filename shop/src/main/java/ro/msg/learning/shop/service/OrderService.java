package ro.msg.learning.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.ProductOrder;
import ro.msg.learning.shop.domain.key.OrderDetailKey;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.strategy.LocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final LocationStrategy locationStrategy;

    @Transactional
    public ProductOrder createOrder(ProductOrder productOrder, List<OrderDetail> orderDetailList) {
        productOrder.setOrderDetailList(new ArrayList<>());
        ProductOrder savedProductOrder = orderRepository.save(productOrder);
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setId(OrderDetailKey.builder().productId(orderDetail.getProduct().getId()).orderId(savedProductOrder.getId()).build());
            orderDetail.setProductOrder(savedProductOrder);
            OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
            productOrder.getOrderDetailList().add(savedOrderDetail);
        }
        return savedProductOrder;
    }

    public OrderDto makeOrder(ProductOrder productOrder) {
        List<OrderDetail> orderDetailList = productOrder.getOrderDetailList();
        List<OrderDetail> ordersWithLocations = locationStrategy.getOrderDetailsByLocationStrategy(orderDetailList);
        productOrder.setCreatedAt(LocalDateTime.now());
        ProductOrder createdProductOrder = this.createOrder(productOrder, ordersWithLocations);
        return orderMapper.toOrderDto(createdProductOrder);
    }

    public List<OrderDto> getAllDtoOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderDto).toList();
    }
}
