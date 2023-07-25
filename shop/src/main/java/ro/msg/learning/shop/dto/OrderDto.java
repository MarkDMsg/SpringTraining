package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Address;
import ro.msg.learning.shop.domain.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private UUID id;
    private UUID customerId;
    private LocalDateTime createdAt;
    private List<OrderDetailDto> orderDetailDtoList;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private Customer customer;
    private Address address;
}
