package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ro.msg.learning.shop.Domain.BaseClassesForIds.OrderDetailId;

@Entity
@NoArgsConstructor
@Data
@IdClass(OrderDetailId.class)
@Table(name = "order_detail")
public class OrderDetail {
    private Integer quantity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders")
    private Orders orders;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="shipped_from")
    private Location shippedFrom ;
}
