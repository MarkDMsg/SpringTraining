package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.Keys.StockKey;
import ro.msg.learning.shop.Domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,StockKey> {
}
