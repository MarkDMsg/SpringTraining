package ro.msg.learning.shop.domain.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "\"order_id\"")
    private UUID orderId;

    @Column(name = "product_id")
    private UUID productId;
}
