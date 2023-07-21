package ro.msg.learning.shop.domain.Keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "\"order\"")
    private UUID order;

    @Column(name = "product")
    private UUID product;
}
