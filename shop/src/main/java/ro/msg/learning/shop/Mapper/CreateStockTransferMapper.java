package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.CreateStockDto;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;

@Component
public class CreateStockTransferMapper {
    public static Stock toEntity(CreateStockDto createStockDto, Product product, Location location) {
        return Stock.builder()
                .quantity(createStockDto.getQuantity())
                .location(location)
                .product(product)
                .build();
    }

    public static CreateStockDto toDto(Stock stock) {
        return CreateStockDto.builder()
                .locationId(stock.getLocation().getId())
                .productId(stock.getProduct().getId())
                .quantity(stock.getQuantity())
                .build();
    }
}
