package ro.msg.learning.shop.Strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class MostAbundantLocation implements IStrategy {
    private final IStockRepository stockRepository;
    public static final String STOCK_NOT_FOUND = "Stock not found: ";
    public static final String QUANTITY_NOT_FOUND = "Quantity not found: ";

    @Override
    public List<Stock> retrieveSuitableStock(List<UUID> products, List<Integer> quantities) {
        List<Stock> stocksToBeModified = this.stockRepository.findStocksWithLargestQuantityByProductIds(products);
        if(stocksToBeModified.size()< products.size())
            throw new NotFoundException(STOCK_NOT_FOUND);
        for(Stock stock:stocksToBeModified){
            int productPosition=products.indexOf(stock.getProduct().getId());
            if(quantities.get(productPosition)>stock.getQuantity())
                throw new NotFoundException(QUANTITY_NOT_FOUND);
        }
        return stocksToBeModified;
    }


}
