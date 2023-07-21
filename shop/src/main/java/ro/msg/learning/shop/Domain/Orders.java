package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Orders extends EntityId {
    private LocalDateTime created_at;
    private  String country;
    private String city;
    private String county;
    @Column(name="street_address")
    private String streetAddress;

    @ManyToOne
    @JdbcTypeCode(SqlTypes.UUID)
    @JoinColumn(name="customer")
    private Customer customer;

}
