package ro.msg.learning.shop.Strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SingleLocation implements IStrategy {
    public static final String STOCK_NOT_FOUND = "Stock not found: ";
    public static final String LOCATION_WITH_ALL_PRODUCTS_NOT_FOUND = "Location with all products not found: ";
    public static final String STOCKS_FROM_SINGLE_LOCATION_WITH_ENOUGH_QUANTITY_NOT_FOUND = "Stocks from a single location with enough not found: ";
    private final IStockRepository stockRepository;

    public List<Stock> retrieveSuitableStock(List<UUID> products, List<Integer> quantities) {
        //Find locations with all products
        List<Location> locationsIdsWithAllProducts = this.stockRepository.findLocationsWithAllProducts(products, products.size());
        if (locationsIdsWithAllProducts.size() == 0)
            throw new NotFoundException(LOCATION_WITH_ALL_PRODUCTS_NOT_FOUND);

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
                Optional<Stock> stock = stockRepository.findByProductIdAndLocationId(products.get(i), possibleLocationsId.getId());
                stoksList.add(stock.get());
                if (stock.get().getQuantity() < quantities.get(i))
                    enoughQuantity = false;

            }
            //if the quantity was enough for every product
            if (enoughQuantity) {
                return stoksList;
            }
        }
        throw new NotFoundException(STOCKS_FROM_SINGLE_LOCATION_WITH_ENOUGH_QUANTITY_NOT_FOUND);
    }

}
