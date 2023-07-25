package ro.msg.learning.shop.Domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "customer")
public class Customer extends EntityId {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String password;
    @Column(name = "email_address")
    private String emailAddress;

}
