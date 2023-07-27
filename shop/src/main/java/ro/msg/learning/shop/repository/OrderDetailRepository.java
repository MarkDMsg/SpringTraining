package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.domain.OrderDetail;
import ro.msg.learning.shop.domain.key.OrderDetailKey;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}
