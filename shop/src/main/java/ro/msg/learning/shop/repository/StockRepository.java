package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.domain.Stock;
import ro.msg.learning.shop.domain.key.StockKey;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, StockKey> {

    @Query(value = "SELECT * FROM Stock s WHERE s.product_id=?1 AND s.location_id=?2 ", nativeQuery = true)
    Optional<Stock> findByProductAndLocation(@Param("productID") UUID productID, @Param("locationID") UUID locationId);

    @Query(value = " SELECT s1.product_id AS product_id, s1.location_id AS location_id, s1.quantity AS quantity" +
            " FROM Stock s1 " +
            " WHERE s1.product_id=?1 AND (s1.product_id, s1.quantity) IN " +
            "   (SELECT s2.product_id, MAX(s2.quantity) " +
            "    FROM Stock s2 " +
            "    GROUP BY s2.product_id ) ", nativeQuery = true)
    List<Stock> findStockWithMaximumQuantityOnLocationForProduct(@Param("requiredProductId") UUID productId);
}
