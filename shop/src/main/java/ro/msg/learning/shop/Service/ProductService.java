package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Exception.AlreadyExistsException;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Mapper.ProductAndProductCategoryTranslatorMapper;
import ro.msg.learning.shop.Repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists: ";
    public static final String PRODUCT_CATEGORY_NOT_FOUND = "Product category not found: ";
    public static final String PRODUCT_NOT_FOUND = "Product not found: ";
    private final ProductCategoryService productCategoryService;
    private final IProductRepository productRepository;
    private final LocationService locationService;
    private final StockService stockService;

    public ProductAndProductCategoryDto createProduct(ProductAndProductCategoryDto productAndProductCategoryDto) {
        //Validate the miss of the product in the product list
        if (productRepository.existsByAttributes(
                productAndProductCategoryDto.getProductName(),
                productAndProductCategoryDto.getProductDescription(),
                productAndProductCategoryDto.getProductPrice(),
                productAndProductCategoryDto.getProductWeight(),
                productAndProductCategoryDto.getProductSupplier(),
                productAndProductCategoryDto.getProductImageUrl(),
                productAndProductCategoryDto.getProductCategoryId())) {
            throw new AlreadyExistsException(PRODUCT_ALREADY_EXISTS + productAndProductCategoryDto.getProductName());
        }
        //Validate the existence of the product category in the product list
        ProductCategory productCategory = this.productCategoryService.findProductCategoryById(productAndProductCategoryDto.getProductCategoryId());
        Product newProduct = ProductAndProductCategoryTranslatorMapper.toEntity(productAndProductCategoryDto, productCategory);
        return ProductAndProductCategoryTranslatorMapper.toDto(productRepository.save(newProduct));
    }

    public List<ProductAndProductCategoryDto> getAllProducts() {
        List<ProductAndProductCategoryDto> productCategorySimpleDtoList = new ArrayList<ProductAndProductCategoryDto>();
        productRepository.findAll().forEach(product -> productCategorySimpleDtoList.add(ProductAndProductCategoryTranslatorMapper.toDto(product)));
        return productCategorySimpleDtoList;
    }

    public void deleteProduct(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            //Remove stocks
            this.stockService.removeStocksForAProduct(product.get());
            //Remove product
            productRepository.delete(product.get());

        } else {
            throw new NotFoundException(PRODUCT_NOT_FOUND + id);

        }
    }

    public ProductAndProductCategoryDto getProductAndProductCategoryById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductAndProductCategoryTranslatorMapper.toDto(product.get());
        } else {
            throw new NotFoundException(PRODUCT_NOT_FOUND + id);
        }
    }

    public Product getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NotFoundException(PRODUCT_NOT_FOUND + id);
        }
    }

    public ProductAndProductCategoryDto updateProduct(ProductAndProductCategoryDto productAndProductCategoryDto) throws Exception {
        //Validate the existence of the product in the product list
        Optional<Product> product = productRepository.findById(productAndProductCategoryDto.getProductId());
        if (product.isPresent()) {
            throw new NotFoundException(PRODUCT_NOT_FOUND + productAndProductCategoryDto.getProductId());
        }
        //Validate the existence of the product category in the product list
        ProductCategory productCategory = this.productCategoryService.findProductCategoryById(productAndProductCategoryDto.getProductCategoryId());
        if (productCategory == null) {
            throw new NotFoundException(PRODUCT_CATEGORY_NOT_FOUND + productAndProductCategoryDto.getProductId());
        }
        Product savedProduct = productRepository.save(ProductAndProductCategoryTranslatorMapper.toEntity(productAndProductCategoryDto, productCategory));
        return ProductAndProductCategoryTranslatorMapper.toDto(savedProduct);
    }
}
