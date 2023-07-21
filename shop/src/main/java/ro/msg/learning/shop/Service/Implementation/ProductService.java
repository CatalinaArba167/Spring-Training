package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Domain.Stock;
import ro.msg.learning.shop.Exception.*;
import ro.msg.learning.shop.Mapper.ProductAndProductCategoryTranslatorMapper;
import ro.msg.learning.shop.Repository.ILocationRepository;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;
import ro.msg.learning.shop.Repository.IProductRepository;
import ro.msg.learning.shop.Repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private LocationService locationService;

    @Autowired
    private StockService stockService;



    public ProductAndProductCategoryDto createProduct (ProductAndProductCategoryDto productAndProductCategoryDto) {
        //Validate the miss of the product in the product list
        if (productRepository.existsByAttributes(
                productAndProductCategoryDto.getProductName(),
                productAndProductCategoryDto.getProductDescription(),
                productAndProductCategoryDto.getProductPrice(),
                productAndProductCategoryDto.getProductWeight(),
                productAndProductCategoryDto.getProductSupplier(),
                productAndProductCategoryDto.getProductImageUrl(),
                productAndProductCategoryDto.getProductCategoryId())) {
            throw new ProductAlreadyExistingException(productAndProductCategoryDto.getProductName());
        }
        //Validate the existence of the product category in the product list
        ProductCategory productCategory=this.productCategoryService.findProductCategoryById(productAndProductCategoryDto.getProductCategoryId());
        if(productCategory==null)
        {
            throw new ProductCategoryNotFoundException(productAndProductCategoryDto.getProductId());
        }

        //Validate the existence of the location in the product list
        Location location=this.locationService.findById(productAndProductCategoryDto.getLocationId());


        ProductAndProductCategoryTranslatorMapper mapper=new ProductAndProductCategoryTranslatorMapper();
        Product newProduct=mapper.toProduct(productAndProductCategoryDto,productCategory);
        ProductAndProductCategoryDto newProductAndProductCategoryDto= mapper.toProductAndProductCategoryDto(productRepository.save(newProduct));
        //Create the stock
        this.stockService.createStock(newProduct,location,productAndProductCategoryDto.getStockQuantity());
        return newProductAndProductCategoryDto;
    }

    public List<ProductAndProductCategoryDto> getAllProducts(){
        List<ProductAndProductCategoryDto> productCategorySimpleDtoList = new ArrayList<ProductAndProductCategoryDto>();
        ProductAndProductCategoryTranslatorMapper mapper=new ProductAndProductCategoryTranslatorMapper();
        productRepository.findAll().forEach(product ->  productCategorySimpleDtoList.add(mapper.toProductAndProductCategoryDto(product)));
        return productCategorySimpleDtoList;
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id);
        ProductAndProductCategoryTranslatorMapper mapper=new ProductAndProductCategoryTranslatorMapper();
        if (product != null) {
            this.stockService.removeStocksForAProduct(product);
            productRepository.delete(product);
        }
        else {
            throw new ProductNotFoundException(id);

        }
    }
    public ProductAndProductCategoryDto getProductById(UUID id) {
        ProductAndProductCategoryTranslatorMapper mapper=new ProductAndProductCategoryTranslatorMapper();
        Product product=productRepository.findById(id);
        if(product!=null){
            return mapper.toProductAndProductCategoryDto(product);
        }
        else{
            throw new ProductNotFoundException(id);
        }
    }

    public void updateProduct(ProductAndProductCategoryDto productAndProductCategoryDto) {
        //Validate the existence of the product in the product list
        Product product=productRepository.findById(productAndProductCategoryDto.getProductId());
        if(product==null){
            throw new ProductNotFoundException(productAndProductCategoryDto.getProductId());
        }
        //Validate the existence of the product category in the product list
        ProductCategory productCategory=this.productCategoryService.findProductCategoryById(productAndProductCategoryDto.getProductCategoryId());
        if(productCategory==null)
        {
            throw new ProductCategoryNotFoundException(productAndProductCategoryDto.getProductId());
        }
        ProductAndProductCategoryTranslatorMapper mapper=new ProductAndProductCategoryTranslatorMapper();
        System.out.println((productRepository.save(mapper.toProduct(productAndProductCategoryDto,productCategory))));
    }
}
