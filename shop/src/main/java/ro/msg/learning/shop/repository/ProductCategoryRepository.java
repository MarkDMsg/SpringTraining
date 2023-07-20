package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.domain.ProductCategory;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    ProductCategory findByName(String name);

    ProductCategory getByName(String name);

    boolean existsByName(String name);
}