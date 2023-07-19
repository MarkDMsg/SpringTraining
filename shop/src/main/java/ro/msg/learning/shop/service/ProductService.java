package ro.msg.learning.shop.service;

import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductWithCategoryDto createProduct(String name, String description, BigDecimal price, Double weight,String supplier,String imageUrl, ProductCategory productCategory );

    ProductWithCategoryDto updateProduct(UUID productId, String newName, String newDescription, BigDecimal newPrice, Double newWeight, String newSupplier, String newImageUrl, ProductCategory newCategory);

    void deleteProductById(UUID productId);

    ProductWithCategoryDto getProductById(UUID productId);

    List<ProductWithCategoryDto> getAllProducts();

}
