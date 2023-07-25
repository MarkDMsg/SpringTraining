package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductDto;

@Component
public class ProductMapper {
    public ProductDto toProductWithCategoryDto(Product product, ProductCategory productCategory) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getWeight(), product.getSupplier(), product.getImageUrl(), productCategory.getId(), productCategory.getName(), productCategory.getDescription());
    }

    public Product toProduct(ProductDto productDto) {
        ProductCategory productCategory = new ProductCategory(productDto.getName(), productDto.getCategoryDescription());
        return new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getWeight(), productCategory, productDto.getSupplier(), productDto.getImageUrl());
    }
}
