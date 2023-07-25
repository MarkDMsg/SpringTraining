package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.domain.OrderDetail;

import java.util.List;

public interface LocationStrategy {

    List<OrderDetail> getOrderDetailsByLocationStrategy(List<OrderDetail> orderDetailList);
}
