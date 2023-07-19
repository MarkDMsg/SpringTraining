package ro.msg.learning.shop.Domain;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class EntityWithUUID {
    @Id
    @GeneratedValue
    private UUID id;

}
