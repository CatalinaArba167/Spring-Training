package ro.msg.learning.shop.Domain.BaseClassesForIds;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Embeddable
@Setter
@Getter
public class StockId implements Serializable {
    private UUID product;
    private UUID location;
}
