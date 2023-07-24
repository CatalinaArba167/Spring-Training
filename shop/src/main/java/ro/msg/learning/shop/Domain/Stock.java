package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.BaseClassesForIds.StockId;

@Entity
@IdClass(StockId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
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
