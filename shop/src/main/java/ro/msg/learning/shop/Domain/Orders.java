package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Table(name = "orders")
public class Orders extends EntityId {
    @Column(name = "created_at")
    private Timestamp createdAt;
    private String country;
    private String city;
    private String county;
    @Column(name = "street_address")
    private String streetAddress;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;


}
