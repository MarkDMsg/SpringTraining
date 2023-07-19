package ro.msg.learning.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategoryDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String supplier;
    private String imageUrl;
    private String categoryName;
    private String categoryDescription;
}
