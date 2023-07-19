package ro.msg.learning.shop.Domain.Keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class StockKey implements Serializable {
    @Column(name = "product")
    private UUID product;

    @Column(name = "location")
    private UUID location;
}
