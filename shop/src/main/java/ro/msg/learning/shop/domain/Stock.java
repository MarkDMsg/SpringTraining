package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.domain.key.StockKey;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @EmbeddedId
    @Id
    private StockKey id;

    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;


    @Column(name = "quantity")
    private int quantity;
}
