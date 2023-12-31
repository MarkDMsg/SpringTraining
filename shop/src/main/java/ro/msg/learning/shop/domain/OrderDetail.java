package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.key.OrderDetailKey;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location shippedFrom;

    @Column(name = "quantity")
    private Integer quantity;
}
