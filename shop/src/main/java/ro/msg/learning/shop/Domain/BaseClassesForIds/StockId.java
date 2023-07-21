package ro.msg.learning.shop.Domain.BaseClassesForIds;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Embeddable
public  class StockId implements Serializable {
    private UUID product;
    private UUID location;
}
