package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StockService {
    @Autowired
    private IStockRepository stockRepository;
    @Autowired
    private LocationService locationService;

    public void removeStocksForAProduct(Product product) {
        List<Stock> stocks = stockRepository.findByProduct(product);
        stockRepository.deleteAll(stocks);
    }

    public List<Stock> getStockFromMostAbundantLocation(List<UUID>products,List<Integer> quantities){
        List<Stock> stocksToBeModified=this.stockRepository.findStocksWithLargestQuantityByProductIds(products);
        return this.modifyStock(products,quantities,stocksToBeModified);
    }


    public List<Stock> modifyStock(List<UUID> products, List<Integer> quantities, List<Stock> stocks) {
        Integer noOfProducts = products.size();
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
