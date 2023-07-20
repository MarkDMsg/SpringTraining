package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Keys.OrderDetailKey;

import java.util.List;

@Table(name = "orderdetail")
@Entity
@Data
@Builder
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
    @MapsId("products")
    @JoinColumn(name = "product")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="shippedfrom")
    private Location shippedFrom;

    @Column(name="quantity")
    private List<Integer> quantities;
}
