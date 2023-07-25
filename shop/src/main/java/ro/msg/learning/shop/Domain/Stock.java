package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.Domain.BaseClassesForIds.StockId;

@Entity
@IdClass(StockId.class)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Table(name = "stock")
public class Stock {
    private Integer quantity;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private Location location;
}
