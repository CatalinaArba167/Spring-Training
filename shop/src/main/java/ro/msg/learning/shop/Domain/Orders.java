package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
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
    @JdbcTypeCode(SqlTypes.UUID)
    @JoinColumn(name = "customer")
    private Customer customer;


}
