package ro.msg.learning.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Address {
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
