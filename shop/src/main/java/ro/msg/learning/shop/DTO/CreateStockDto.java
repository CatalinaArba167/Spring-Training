package ro.msg.learning.shop.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
public class CreateStockDto {
    private Integer quantity;
    private UUID productId;
    private UUID locationId;
}
