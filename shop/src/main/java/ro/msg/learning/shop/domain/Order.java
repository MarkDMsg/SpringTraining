package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends EntityWithUUID {
    @ManyToOne
    @MapsId("costumer")
    @JoinColumn(name = "id")
    private UUID customerId;

    @Column(name="createdat")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "address_country")),
            @AttributeOverride( name = "city", column = @Column(name = "address_city")),
            @AttributeOverride( name = "county", column = @Column(name = "address_county")),
            @AttributeOverride( name = "streetAddress", column = @Column(name = "address_streetaddress"))
    })
    private Address address;
}
