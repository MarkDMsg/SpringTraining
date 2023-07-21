package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.domain.ProductCategory;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;

@Component
public class ProductMapper {
    public ProductWithCategoryDto toProductWithCategoryDto(Product product, ProductCategory productCategory){
        return new ProductWithCategoryDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getWeight(),product.getSupplier(),product.getImageUrl(),productCategory.getId(),productCategory.getName(),productCategory.getDescription());
    }

    public Product toProduct(ProductWithCategoryDto productWithCategoryDto){
        ProductCategory productCategory=new ProductCategory(productWithCategoryDto.getName(),productWithCategoryDto.getCategoryDescription());
        return new Product(productWithCategoryDto.getName(),productWithCategoryDto.getDescription(),productWithCategoryDto.getPrice(),productWithCategoryDto.getWeight(),productCategory,productWithCategoryDto.getSupplier(),productWithCategoryDto.getImageUrl());
    }
}
