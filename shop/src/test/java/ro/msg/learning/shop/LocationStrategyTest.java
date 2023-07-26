package ro.msg.learning.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Repository.ILocationRepository;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;
import ro.msg.learning.shop.Repository.IProductRepository;
import ro.msg.learning.shop.Repository.IStockRepository;
import ro.msg.learning.shop.Service.ProductCategoryService;
import ro.msg.learning.shop.Strategy.MostAbundantLocation;
import ro.msg.learning.shop.Strategy.SingleLocation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationStrategyTest {
    public static final String LOCATION_WITH_ALL_PRODUCTS_NOT_FOUND = "Location with all products not found: ";
    public static final String STOCK_NOT_FOUND = "Stock not found: ";
    public static final String QUANTITY_NOT_FOUND = "Quantity not found: ";
    public static final String STOCKS_FROM_SINGLE_LOCATION_WITH_ENOUGH_QUANTITY_NOT_FOUND = "Stocks from a single location with enough not found: ";

    @Mock
    private IStockRepository stockRepository;

    @Test
    public void mostAbundantLocationTest() {
        MostAbundantLocation mostAbundantLocation=new MostAbundantLocation(stockRepository);
        //INITIALIZATION
        //PRODUCT CATEGORIES
        ProductCategory category1 = new ProductCategory("Category 1", "Description for Category 1");
        ProductCategory category2 = new ProductCategory("Category 2", "Description for Category 2");
        //PRODUCTS
        Product product1 = new Product("Product 1", "Description for Product 1", BigDecimal.valueOf(29.99), 1.5, "Supplier 1", "https://example.com/product1.jpg", category1);
        Product product2 = new Product("Product 2", "Description for Product 2", BigDecimal.valueOf(19.99), 0.8, "Supplier 2", "https://example.com/product2.jpg", category2);
        Product product3 = new Product("Product 3", "Description for Product 3", BigDecimal.valueOf(39.99), 2.0, "Supplier 1", "https://example.com/product3.jpg", category1);
        Product product4 = new Product("Product 4", "Description for Product 4", BigDecimal.valueOf(14.99), 1.2, "Supplier 3", "https://example.com/product4.jpg", category2);
        Product product5 = new Product("Product 5", "Description for Product 5", BigDecimal.valueOf(49.99), 1.8, "Supplier 2", "https://example.com/product5.jpg", category2);
        product1.setId(UUID.randomUUID());
        product2.setId(UUID.randomUUID());
        product3.setId(UUID.randomUUID());
        product4.setId(UUID.randomUUID());
        product5.setId(UUID.randomUUID());
        //LOCATIONS
        Location location1 = new Location("Location 1", "Country 1", "City 1", "County 1", "Address 1");
        Location location2 = new Location("Location 2", "Country 2", "City 2", "County 2", "Address 2");
        location1.setId(UUID.randomUUID());
        location2.setId(UUID.randomUUID());
        //STOCKS
        Stock stock1 = new Stock(10, product1, location1);
        Stock stock2 = new Stock(10, product2, location1);
        Stock stock3 = new Stock(10, product3, location1);
        Stock stock4 = new Stock(11, product4, location1);
        Stock stock5 = new Stock(5, product1, location2);
        Stock stock6 = new Stock(5, product2, location2);
        Stock stock7 = new Stock(5, product4, location2);

        //TEST
        //p1,p2,p5=> ERROR(NO STOCK CONTAINS P5)
        List<UUID> productsListTest1=new ArrayList<>();
        productsListTest1.add(product1.getId());
        productsListTest1.add(product2.getId());
        productsListTest1.add(product5.getId());
        List<Integer> quantityListTest1 = new ArrayList<>();
        quantityListTest1.add(1);
        quantityListTest1.add(1);
        quantityListTest1.add(1);
        List<Stock>  stockListTest1=new ArrayList<>();
        stockListTest1.add(stock5);
        stockListTest1.add(stock2);
        when(stockRepository.findStocksWithLargestQuantityByProductIds(productsListTest1)).thenReturn(stockListTest1);
        try {
            mostAbundantLocation.retrieveSuitableStock(productsListTest1,quantityListTest1);
        } catch (NotFoundException exception) {
            assertFalse(exception.getMessage().isEmpty());
            assertEquals(STOCK_NOT_FOUND, exception.getMessage());
        }
        //TEST 2
        //p1,p2,p4 quantity 11=>ERROR(NOT ENOUGH QUANTITY)
        List<UUID> productsListTest2=new ArrayList<>();
        productsListTest2.add(product1.getId());
        productsListTest2.add(product2.getId());
        productsListTest2.add(product4.getId());
        List<Integer> quantityListTest2 = new ArrayList<>();
        quantityListTest2.add(11);
        quantityListTest2.add(11);
        quantityListTest2.add(11);
        List<Stock>  stockListTest2=new ArrayList<>();
        stockListTest2.add(stock5);
        stockListTest2.add(stock4);
        stockListTest2.add(stock2);
        when(stockRepository.findStocksWithLargestQuantityByProductIds(productsListTest2)).thenReturn(stockListTest2);
        try {
            mostAbundantLocation.retrieveSuitableStock(productsListTest2,quantityListTest2);
        } catch (NotFoundException exception) {
            assertFalse(exception.getMessage().isEmpty());
            assertEquals(QUANTITY_NOT_FOUND, exception.getMessage());
        }
    }

    @Test
    public void singleLocationTest() {
        SingleLocation singleLocation = new SingleLocation(stockRepository);

        //INITIALIZATION
        //PRODUCT CATEGORIES
        ProductCategory category1 = new ProductCategory("Category 1", "Description for Category 1");
        ProductCategory category2 = new ProductCategory("Category 2", "Description for Category 2");
        //PRODUCTS
        Product product1 = new Product("Product 1", "Description for Product 1", BigDecimal.valueOf(29.99), 1.5, "Supplier 1", "https://example.com/product1.jpg", category1);
        Product product2 = new Product("Product 2", "Description for Product 2", BigDecimal.valueOf(19.99), 0.8, "Supplier 2", "https://example.com/product2.jpg", category2);
        Product product3 = new Product("Product 3", "Description for Product 3", BigDecimal.valueOf(39.99), 2.0, "Supplier 1", "https://example.com/product3.jpg", category1);
        Product product4 = new Product("Product 4", "Description for Product 4", BigDecimal.valueOf(14.99), 1.2, "Supplier 3", "https://example.com/product4.jpg", category2);
        Product product5 = new Product("Product 5", "Description for Product 5", BigDecimal.valueOf(49.99), 1.8, "Supplier 2", "https://example.com/product5.jpg", category2);
        product1.setId(UUID.randomUUID());
        product2.setId(UUID.randomUUID());
        product3.setId(UUID.randomUUID());
        product4.setId(UUID.randomUUID());
        product5.setId(UUID.randomUUID());
        //LOCATIONS
        Location location1 = new Location("Location 1", "Country 1", "City 1", "County 1", "Address 1");
        Location location2 = new Location("Location 2", "Country 2", "City 2", "County 2", "Address 2");
        location1.setId(UUID.randomUUID());
        location2.setId(UUID.randomUUID());
        //STOCKS
        Stock stock1 = new Stock(10, product1, location1);
        Stock stock2 = new Stock(10, product2, location1);
        Stock stock3 = new Stock(10, product3, location1);
        Stock stock4 = new Stock(5, product1, location2);
        Stock stock5 = new Stock(5, product2, location2);
        Stock stock6 = new Stock(5, product4, location2);

//        when(stockRepository.findByProductIdAndLocationId(product1.getId(),location1.getId())).thenReturn(Optional.of(stock1));
//        when(stockRepository.findByProductIdAndLocationId(product2.getId(),location1.getId())).thenReturn(Optional.of(stock2));
//        when(stockRepository.findByProductIdAndLocationId(product3.getId(),location1.getId())).thenReturn(Optional.of(stock3));
        when(stockRepository.findByProductIdAndLocationId(product1.getId(), location2.getId())).thenReturn(Optional.of(stock4));
        when(stockRepository.findByProductIdAndLocationId(product2.getId(), location2.getId())).thenReturn(Optional.of(stock5));
        when(stockRepository.findByProductIdAndLocationId(product4.getId(), location2.getId())).thenReturn(Optional.of(stock6));

        //TEST 1
        //p1,p2,p4 with quantity 1=>L2
        //L1 does not contain all the productList(p4 missing)
        List<UUID> test1ProductList = new ArrayList<>();
        test1ProductList.add(product1.getId());
        test1ProductList.add(product2.getId());
        test1ProductList.add(product4.getId());
        List<Integer> test1QuantityList = new ArrayList<>();
        test1QuantityList.add(1);
        test1QuantityList.add(1);
        test1QuantityList.add(1);
        List<Location> test1LocationList = new ArrayList<>();
        test1LocationList.add(location2);
        when(stockRepository.findLocationsWithAllProducts(test1ProductList, test1QuantityList.size())).thenReturn(test1LocationList);
        List<Stock> testIReturnedStockList = new ArrayList<>();
        testIReturnedStockList.add(stock4);
        testIReturnedStockList.add(stock5);
        testIReturnedStockList.add(stock6);
        Assertions.assertTrue(singleLocation.retrieveSuitableStock(test1ProductList, test1QuantityList).containsAll(testIReturnedStockList));

        //TEST2
        //p1,p2,p4 with quantity 11=> ERROR
        //L1,L2 does not have enough quantity
        List<Integer> test2QuantityList = new ArrayList<>();
        test2QuantityList.add(11);
        test2QuantityList.add(11);
        test2QuantityList.add(11);
        when(stockRepository.findLocationsWithAllProducts(test1ProductList, test1ProductList.size())).thenReturn(test1LocationList);
        try {
            singleLocation.retrieveSuitableStock(test1ProductList, test2QuantityList);
        } catch (NotFoundException exception) {
            assertFalse(exception.getMessage().isEmpty());
            assertEquals(STOCKS_FROM_SINGLE_LOCATION_WITH_ENOUGH_QUANTITY_NOT_FOUND, exception.getMessage());
        }

        //TEST 3
        //p1,p2,p5 with quantity 1=>no location contains p5=>error
        List<UUID> test3ProductList = new ArrayList<>();
        test3ProductList.add(product1.getId());
        test3ProductList.add(product2.getId());
        test3ProductList.add(product5.getId());
        List<Location> test3LocationList = new ArrayList<>();
        when(stockRepository.findLocationsWithAllProducts(test3ProductList, test1QuantityList.size())).thenReturn(test3LocationList);
        try {
            singleLocation.retrieveSuitableStock(test3ProductList, test1QuantityList);
        } catch (NotFoundException exception) {
            assertFalse(exception.getMessage().isEmpty());
            assertEquals(LOCATION_WITH_ALL_PRODUCTS_NOT_FOUND, exception.getMessage());
        }
    }
}
