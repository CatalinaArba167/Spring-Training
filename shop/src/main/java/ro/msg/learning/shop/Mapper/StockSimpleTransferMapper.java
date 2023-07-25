package ro.msg.learning.shop.Mapper;

import ro.msg.learning.shop.DTO.StockSimpleDto;
import ro.msg.learning.shop.Domain.Stock;

public class StockSimpleTransferMapper {
    public static StockSimpleDto toDto(Stock stock) {
        return StockSimpleDto.builder()
                .quantity(stock.getQuantity())
                .product(stock.getProduct())
                .location(stock.getLocation())
                .build();
    }

    public static Stock toEntity(StockSimpleDto stockSimpleDto) {
        return Stock.builder()
                .quantity(stockSimpleDto.getQuantity())
                .product(stockSimpleDto.getProduct())
                .location(stockSimpleDto.getLocation())
                .build();

    }
}
