package ro.msg.learning.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.Repository.ILocationRepository;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;
import ro.msg.learning.shop.Repository.IProductRepository;
import ro.msg.learning.shop.Repository.IStockRepository;
import ro.msg.learning.shop.Service.ProductCategoryService;

@ExtendWith(MockitoExtension.class)
public class LocationStrategyTest {
    @Mock
    private IStockRepository stockRepository;

    @InjectMocks
    private ProductCategoryService productCategoryService;
    @Mock
    private IProductRepository productRepository;

    @Mock
    private ILocationRepository locationRepository;

    @Mock
    private IProductCategoryRepository productCategoryRepository;

    @Test
    public void singleLocationTest() {
//        SingleLocation singleLocation = new SingleLocation(stockRepository);
//
//        // Create two ProductCategory instances representing categories
//        ProductCategory category1 = new ProductCategory("Category 1", "Description for Category 1");
//        ProductCategory category2 = new ProductCategory("Category 2", "Description for Category 2");
//        List<ProductCategory> productCategoryList=new ArrayList<>();
//        productCategoryList.add(category1);
//        productCategoryList.add(category2);
//        when(productCategoryRepository.findAll()).thenReturn(productCategoryList);
//        Assertions.assertEquals(productCategoryList,productCategoryService.getAllProductCategories());


//        // Products
//        Product product1 = new Product("Product 1", "Description for Product 1", BigDecimal.valueOf(29.99), 1.5, "Supplier 1", "https://example.com/product1.jpg", category1);
//        Product product2 = new Product("Product 2", "Description for Product 2", BigDecimal.valueOf(19.99), 0.8, "Supplier 2", "https://example.com/product2.jpg", category2);
//        Product product3 = new Product("Product 3", "Description for Product 3", BigDecimal.valueOf(39.99), 2.0, "Supplier 1", "https://example.com/product3.jpg", category1);
//        Product product4 = new Product("Product 4", "Description for Product 4", BigDecimal.valueOf(14.99), 1.2, "Supplier 3", "https://example.com/product4.jpg", category2);
//        Product product5 = new Product("Product 5", "Description for Product 5", BigDecimal.valueOf(49.99), 1.8, "Supplier 2", "https://example.com/product5.jpg", category2);
//
//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//        productRepository.save(product4);
//        productRepository.save(product5);
//
//        Location location1 = new Location("Location 1", "Country 1", "City 1", "County 1", "Address 1");
//        Location location2 = new Location("Location 2", "Country 2", "City 2", "County 2", "Address 2");
//
//        locationRepository.save(location1);
//        locationRepository.save(location2);
//
//        Stock stock1 = new Stock(10, product1, location1);
//        Stock stock2 = new Stock(10, product2, location1);
//        Stock stock3 = new Stock(10, product3, location1);
//        Stock stock4 = new Stock(5, product1, location2);
//        Stock stock5 = new Stock(5, product2, location2);
//        Stock stock6 = new Stock(5, product4, location2);
//        List<Stock> stockList=new ArrayList<>();
//        //L1:p1,p2,p3 quantity 10
//        //L2:p1,p2,p4 quantity 10
//        stockList.add(stock1);
//        stockList.add(stock2);
//        stockList.add(stock3);
//        stockList.add(stock4);
//        stockList.add(stock5);
//        stockList.add(stock6);
//
//        //p1,p2,p4 with quantity 1
//        //L1 does not contain all the products(p4 missing)
//        List<UUID> test1ProductList=new ArrayList<>();
//        test1ProductList.add(product1.getId());
//        test1ProductList.add(product2.getId());
//        test1ProductList.add(product4.getId());
//        List<Integer> test1QuantityList=new ArrayList<>();
//        test1QuantityList.add(1);
//        test1QuantityList.add(1);
//        test1QuantityList.add(1);
//        List<Stock> testIReturnedStockList=new ArrayList<>();
//        testIReturnedStockList.add(stock4);
//        testIReturnedStockList.add(stock5);
//        testIReturnedStockList.add(stock6);
//
//        System.out.println(stockRepository.findAll());
//        System.out.println(stockRepository.count());
//        System.out.println(singleLocation.retrieveSuitableStock(test1ProductList,test1QuantityList));
//        //Assertions.assertTrue( singleLocation.retrieveSuitableStock(test1ProductList,test1QuantityList).containsAll(testIReturnedStockList));
//
//        //p1,p2 with quantity 6
//        //L2 does not have enough quantity
//        List<UUID> test2ProductList=new ArrayList<>();
//        test2ProductList.add(product1.getId());
//        test2ProductList.add(product2.getId());
//        List<Integer> test2QuantityList=new ArrayList<>();
//        test2QuantityList.add(6);
//        test2QuantityList.add(6);
//        List<Stock> test2ReturnedStockList=new ArrayList<>();
//        test2ReturnedStockList.add(stock1);
//        test2ReturnedStockList.add(stock2);
//        Assertions.assertTrue( singleLocation.retrieveSuitableStock(test2ProductList,test2QuantityList).containsAll(test2ReturnedStockList));

    }
}
