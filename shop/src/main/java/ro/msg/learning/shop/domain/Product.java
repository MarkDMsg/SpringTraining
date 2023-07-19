package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends EntityWithUUID {
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="weight")
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "category")
    private ProductCategory category;

    @Column(name="supplier")
    private String supplier;

    @Column(name="imageurl")
    private String imageUrl;
}
