package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.Keys.OrderDetailKey;

@Table(name = "orderdetail")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail{
    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("order")
    @JoinColumn(name = "order")
    private Order order;

    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="shippedfrom")
    private Location shippedFrom;

    @Column(name="quantity")
    private int quantity;
}
