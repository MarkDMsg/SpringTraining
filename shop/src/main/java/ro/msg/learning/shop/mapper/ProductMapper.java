package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.domain.Product;
import ro.msg.learning.shop.dto.ProductWithCategoryDto;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductWithCategoryDto dogSimple);

    ProductWithCategoryDto toDTO(Product product);

}
