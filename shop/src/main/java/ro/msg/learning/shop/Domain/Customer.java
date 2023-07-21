package ro.msg.learning.shop.Domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.SqlTypes;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;
import ro.msg.learning.shop.Domain.BaseClassesForIds.OrderDetailId;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@Table(name = "customer")
public class Customer extends EntityId {
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String username;
    private String password;
    @Column(name="email_address")
    private String emailAddress;

}
