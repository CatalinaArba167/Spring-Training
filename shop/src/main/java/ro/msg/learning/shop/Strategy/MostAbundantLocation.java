package ro.msg.learning.shop.Strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Repository.IStockRepository;
import ro.msg.learning.shop.Service.Implementation.StockService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class MostAbundantLocation implements IStrategy{
    @Autowired
    private IStockRepository stockRepository;

    @Override
    public List<Stock> retrieveSuitableStock(List<UUID> products, List<Integer> quantities) {
        List<Stock> stocksToBeModified=this.stockRepository.findStocksWithLargestQuantityByProductIds(products);
        return stocksToBeModified;//this.modifyStock(products,quantities,stocksToBeModified);
    }


}
