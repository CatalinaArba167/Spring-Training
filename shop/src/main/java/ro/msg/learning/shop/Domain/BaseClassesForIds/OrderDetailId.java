package ro.msg.learning.shop.Domain.BaseClassesForIds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.Orders;
import ro.msg.learning.shop.Domain.Product;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDetailId implements Serializable {
    private UUID orders;
    private UUID product;
}
