package ro.msg.learning.shop.Strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class MostAbundantLocation implements IStrategy {
    private final IStockRepository stockRepository;

    @Override
    public List<Stock> retrieveSuitableStock(List<UUID> products, List<Integer> quantities) {
        List<Stock> stocksToBeModified = this.stockRepository.findStocksWithLargestQuantityByProductIds(products);
        return stocksToBeModified;//this.modifyStock(products,quantities,stocksToBeModified);
    }


}
