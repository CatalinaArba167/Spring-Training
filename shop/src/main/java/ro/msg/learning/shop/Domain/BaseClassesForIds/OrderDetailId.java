package ro.msg.learning.shop.Domain.BaseClassesForIds;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDetailId implements Serializable {
    private UUID orders;
    private UUID product;
}
