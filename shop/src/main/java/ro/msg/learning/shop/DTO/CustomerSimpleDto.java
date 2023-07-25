package ro.msg.learning.shop.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerSimpleDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
}
