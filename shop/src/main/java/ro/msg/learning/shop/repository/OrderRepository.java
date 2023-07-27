package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.domain.ProductOrder;

import java.util.UUID;


public interface OrderRepository extends JpaRepository<ProductOrder, UUID> {
}
