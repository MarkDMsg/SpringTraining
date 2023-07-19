package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location extends EntityWithUUID {
    @Column(name="name")
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "address_country")),
            @AttributeOverride( name = "city", column = @Column(name = "address_city")),
            @AttributeOverride( name = "county", column = @Column(name = "address_county")),
            @AttributeOverride( name = "streetAddress", column = @Column(name = "address_streetaddress"))
    })
    private Address address;
}
