package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.Domain.BaseClassesForIds.OrderDetailId;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
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
    @JoinColumn(name = "shipped_from")
    private Location shippedFrom;
}
