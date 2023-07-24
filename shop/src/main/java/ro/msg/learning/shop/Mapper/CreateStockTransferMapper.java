package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.CreateStockDto;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;

@Component
public class CreateStockTransferMapper {
    public Stock toStock(CreateStockDto createStockDto, Product product, Location location) {
        Stock stock = new Stock();
        stock.setQuantity(createStockDto.getQuantity());
        stock.setLocation(location);
        stock.setProduct(product);
        return stock;
    }

    public CreateStockDto toCreateStockDto(Stock stock) {
        CreateStockDto createStockDto = new CreateStockDto();
        createStockDto.setLocationId(stock.getLocation().getId());
        createStockDto.setProductId(stock.getProduct().getId());
        createStockDto.setQuantity(stock.getQuantity());
        return createStockDto;

    }
}
