package ro.msg.learning.shop.domain.Keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "order")
    private UUID order;

    @Column(name = "products")
    private List<UUID> products;
}
