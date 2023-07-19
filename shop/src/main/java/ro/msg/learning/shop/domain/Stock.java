package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.Keys.StockKey;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock{
    @EmbeddedId
    private StockKey id;

    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @MapsId("location")
    @JoinColumn(name = "location")
    private Location location;


    @Column(name="quantity")
    private int quantity;
}
