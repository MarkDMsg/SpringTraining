package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.Keys.StockKey;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,StockKey> {
    Stock findByProductAndLocation(Product product, Location location);
}
