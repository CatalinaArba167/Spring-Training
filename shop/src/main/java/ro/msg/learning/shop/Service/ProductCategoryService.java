package ro.msg.learning.shop.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductCategorySimpleDto;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Exception.AlreadyExistsException;
import ro.msg.learning.shop.Exception.NotFoundException;
import ro.msg.learning.shop.Mapper.ProductCategorySimpleTranslatorMapper;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    public static final String PRODUCT_CATEGORY_NOT_FOUND = "Product category not found: ";
    public static final String PRODUCT_CATEGORY_ALREADY_EXISTS = "Product category already exists: ";
    private final IProductCategoryRepository productCategoryRepository;

    public ProductCategorySimpleDto createProductCategory(ProductCategorySimpleDto productCategorySimpleDto) {
        if (productCategoryRepository.existsByAttributes(
                productCategorySimpleDto.getName(),
                productCategorySimpleDto.getDescription())) {
            throw new AlreadyExistsException(PRODUCT_CATEGORY_ALREADY_EXISTS + productCategorySimpleDto.getName());
        }
        ProductCategory productCategory = ProductCategorySimpleTranslatorMapper.toEntity(productCategorySimpleDto);
        return ProductCategorySimpleTranslatorMapper.toDto(productCategoryRepository.save(productCategory));
    }

    public ProductCategorySimpleDto deleteProductCategory(UUID id) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if (productCategory.isPresent()) {
            productCategoryRepository.delete(productCategory.get());
        } else {
            throw new NotFoundException(PRODUCT_CATEGORY_NOT_FOUND + id);

        }
        return ProductCategorySimpleTranslatorMapper.toDto(productCategory.get());
    }

    public List<ProductCategorySimpleDto> getAllProductCategories() {
        List<ProductCategorySimpleDto> productCategorySimpleDtoList = new ArrayList<ProductCategorySimpleDto>();
        productCategoryRepository.findAll().forEach(productCategory -> productCategorySimpleDtoList.add(ProductCategorySimpleTranslatorMapper.toDto(productCategory)));
        return productCategorySimpleDtoList;
    }

    public ProductCategory findProductCategoryById(UUID id) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if (productCategory.isPresent())
            return productCategory.get();
        else
            throw new NotFoundException(PRODUCT_CATEGORY_NOT_FOUND + id);
    }

}
