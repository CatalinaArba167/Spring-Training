package ro.msg.learning.shop.Mapper;

import ro.msg.learning.shop.DTO.StockSimpleDto;
import ro.msg.learning.shop.Domain.Stock;

public class StockSimpleTransferMapper {
    public StockSimpleDto toStockSimpleDto(Stock stock) {
        StockSimpleDto stockSimpleDto = new StockSimpleDto();
        stockSimpleDto.setQuantity(stock.getQuantity());
        stockSimpleDto.setProduct(stock.getProduct());
        stockSimpleDto.setLocation(stock.getLocation());
        return stockSimpleDto;
    }

    public Stock toStock(StockSimpleDto stockSimpleDto) {
        Stock stock = new Stock();
        stock.setQuantity(stockSimpleDto.getQuantity());
        stock.setProduct(stockSimpleDto.getProduct());
        stock.setLocation(stockSimpleDto.getLocation());
        return stock;
    }
}
