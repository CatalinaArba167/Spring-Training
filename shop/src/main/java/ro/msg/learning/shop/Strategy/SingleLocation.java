package ro.msg.learning.shop.Strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
public class SingleLocation implements IStrategy{
    @Autowired
    private IStockRepository stockRepository;

    public List<Stock> retrieveSuitableStock(List<UUID> products, List<Integer> quantities) {
        //Find locations with all products
        List<Location> locationsIdsWithAllProducts = this.stockRepository.findLocationsWithAllProducts(products, products.size());
        //The result list
        List<Stock> stoksList = new ArrayList<>();
        //For every location that has all the products in stock
        for (Location possibleLocationsId : locationsIdsWithAllProducts) {
            stoksList.clear();
            //We suppose it is enough quantity for every product
            Boolean enoughQuantity = true;
            Integer noOfProducts = products.size();
            //For every product from the order
            for (int i = 0; i < noOfProducts; i++) {
                //We find the quantity of the stock and check if it is enough
                Stock stock = stockRepository.findByProductIdAndLocationId(products.get(i), possibleLocationsId.getId());
                stoksList.add(stock);
                if (stock.getQuantity() < quantities.get(i))
                    enoughQuantity = false;
            }
            //if the quantity was enough for every product
            if (enoughQuantity == true) {
                System.out.println(stoksList);
                return stoksList;
            }
        }
        return null;
    }

}
