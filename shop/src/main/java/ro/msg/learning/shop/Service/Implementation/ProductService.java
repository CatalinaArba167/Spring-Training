package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Exception.ProductAlreadyExistingException;
import ro.msg.learning.shop.Exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.Exception.ProductNotFoundException;
import ro.msg.learning.shop.Mapper.ProductAndProductCategoryTranslatorMapper;
import ro.msg.learning.shop.Repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    public ProductAndProductCategoryDto createProduct(ProductAndProductCategoryDto productAndProductCategoryDto) throws Exception {
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
        ProductCategory productCategory = this.productCategoryService.findProductCategoryById(productAndProductCategoryDto.getProductCategoryId());
        if (productCategory == null) {
            throw new ProductCategoryNotFoundException(productAndProductCategoryDto.getProductId());
        }

        ProductAndProductCategoryTranslatorMapper mapper = new ProductAndProductCategoryTranslatorMapper();
        Product newProduct = mapper.toProduct(productAndProductCategoryDto, productCategory);
        ProductAndProductCategoryDto newProductAndProductCategoryDto = mapper.toProductAndProductCategoryDto(productRepository.save(newProduct));

        return newProductAndProductCategoryDto;
    }

    public List<ProductAndProductCategoryDto> getAllProducts() {
        List<ProductAndProductCategoryDto> productCategorySimpleDtoList = new ArrayList<ProductAndProductCategoryDto>();
        ProductAndProductCategoryTranslatorMapper mapper = new ProductAndProductCategoryTranslatorMapper();
        productRepository.findAll().forEach(product -> productCategorySimpleDtoList.add(mapper.toProductAndProductCategoryDto(product)));
        return productCategorySimpleDtoList;
    }

    public void deleteProduct(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        ProductAndProductCategoryTranslatorMapper mapper = new ProductAndProductCategoryTranslatorMapper();
        if (product.isPresent()) {
            //Remove stocks
            this.stockService.removeStocksForAProduct(product.get());
            //Remove product
            productRepository.delete(product.get());

        } else {
            throw new ProductNotFoundException(id);

        }
    }

    public ProductAndProductCategoryDto getProductAndProductCategoryById(UUID id) {
        ProductAndProductCategoryTranslatorMapper mapper = new ProductAndProductCategoryTranslatorMapper();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return mapper.toProductAndProductCategoryDto(product.get());
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    public Product getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    public void updateProduct(ProductAndProductCategoryDto productAndProductCategoryDto) throws Exception {
        //Validate the existence of the product in the product list
        Optional<Product> product = productRepository.findById(productAndProductCategoryDto.getProductId());
        if (product.isPresent()) {
            throw new ProductNotFoundException(productAndProductCategoryDto.getProductId());
        }
        //Validate the existence of the product category in the product list
        ProductCategory productCategory = this.productCategoryService.findProductCategoryById(productAndProductCategoryDto.getProductCategoryId());
        if (productCategory == null) {
            throw new ProductCategoryNotFoundException(productAndProductCategoryDto.getProductId());
        }
        ProductAndProductCategoryTranslatorMapper mapper = new ProductAndProductCategoryTranslatorMapper();
        System.out.println((productRepository.save(mapper.toProduct(productAndProductCategoryDto, productCategory))));
    }
}
