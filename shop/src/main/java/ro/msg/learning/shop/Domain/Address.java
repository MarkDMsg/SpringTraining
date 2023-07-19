package ro.msg.learning.shop.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Embeddable
public class Address {
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
