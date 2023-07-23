package ro.msg.learning.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location extends EntityWithUUID {
    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;
}
