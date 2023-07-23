package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.StockKey;


public interface StockRepository extends JpaRepository<Stock, StockKey> {
    Stock findByProductAndLocation(Product product, Location location);
}
