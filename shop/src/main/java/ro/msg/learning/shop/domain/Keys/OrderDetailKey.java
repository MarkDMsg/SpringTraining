package ro.msg.learning.shop.domain.Keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "order")
    private UUID order;

    @Column(name = "product")
    private UUID product;
}
