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
    @MapsId("id")
    @JoinColumn(name = "\"order\"")
    private Order order;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="shippedfrom")
    private Location shippedFrom;

    @Column(name="quantity")
    private Integer quantity;
}
