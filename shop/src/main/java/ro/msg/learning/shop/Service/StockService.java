package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockService {
    private final IStockRepository stockRepository;

    public void removeStocksForAProduct(Product product) {
        List<Stock> stocks = stockRepository.findByProduct(product);
        stockRepository.deleteAll(stocks);
    }


    public List<Stock> modifyStock(List<UUID> products, List<Integer> quantities, List<Stock> stocks) {
        int noOfProducts = products.size();
        for (int i = 0; i < noOfProducts; i++) {
            Stock stock = this.findStockByProduct(stocks, products.get(i));
            stock.setQuantity(stock.getQuantity() - quantities.get(i));
            this.stockRepository.save(stock);
        }
        return stocks;
    }

    private Stock findStockByProduct(List<Stock> stockList, UUID productId) {
        return stockList.stream()
                .filter(stock -> stock.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null); // Return null if the Stock is not found
    }


    public Stock modifyStockQuantity(Stock stock, Integer newQuantity) {
        stock.setQuantity(newQuantity);
        return this.stockRepository.save(stock);
    }

}
