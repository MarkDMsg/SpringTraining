package ro.msg.learning.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "productcategory")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory extends EntityWithUUID {
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
}
