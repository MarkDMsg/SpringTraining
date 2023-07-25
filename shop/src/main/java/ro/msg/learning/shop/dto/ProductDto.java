package ro.msg.learning.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String supplier;
    private String imageUrl;
    private UUID categoryId;
    private String categoryName;
    private String categoryDescription;
}
