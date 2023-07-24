package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithProductsDto {
    private UUID id;
    private UUID customerId;
    private LocalDateTime createdAt;
    private List<OrderDetailDto> productsAndQuantities;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
