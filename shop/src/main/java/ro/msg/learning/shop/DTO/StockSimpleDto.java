package ro.msg.learning.shop.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;

@ToString
@Getter
@Setter
public class StockSimpleDto {
    private Integer quantity;
    private Product product;
    private Location location;
}
