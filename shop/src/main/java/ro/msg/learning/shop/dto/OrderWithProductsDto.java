package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Order;
import ro.msg.learning.shop.domain.Product;

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
    private List<Product> products;
    private List<Integer> quantities;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
