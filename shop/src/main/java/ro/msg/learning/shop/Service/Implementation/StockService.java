package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Exception.StockAlreadyExistsException;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private IStockRepository stockRepository;

    public Stock createStock(Product product, Location location, Integer quantity){
        if(stockRepository.findByProductAndLocation(product,location)!=null){
            throw new StockAlreadyExistsException(product.getName(), location.getName());
        }
        return stockRepository.save(new Stock(quantity,product,location));
    }

    public void removeStocksForAProduct(Product product){
        List<Stock> stocks=stockRepository.findByProduct(product);
        stockRepository.deleteAll(stocks);
    }
}
