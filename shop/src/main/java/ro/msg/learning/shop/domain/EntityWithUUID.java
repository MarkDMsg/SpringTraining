package ro.msg.learning.shop.domain;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@MappedSuperclass
public class EntityWithUUID {
    @Id
    @UuidGenerator
    private UUID id;
}
