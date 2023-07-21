package ro.msg.learning.shop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
}
