package ro.msg.learning.shop.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.ProductCategorySimpleDto;
import ro.msg.learning.shop.Domain.ProductCategory;
import ro.msg.learning.shop.Exception.ProductCategoryAlreadyExistingException;
import ro.msg.learning.shop.Exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.Mapper.ProductCategorySimpleTranslatorMapper;
import ro.msg.learning.shop.Repository.IProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductCategoryService {
    @Autowired
    private IProductCategoryRepository productCategoryRepository;


    public ProductCategorySimpleDto createProductCategory(ProductCategorySimpleDto productCategorySimpleDto) {
        if (productCategoryRepository.existsByAttributes(
                productCategorySimpleDto.getName(),
                productCategorySimpleDto.getDescription())) {
            throw new ProductCategoryAlreadyExistingException(productCategorySimpleDto.getName());
        }
        ProductCategorySimpleTranslatorMapper mapper = new ProductCategorySimpleTranslatorMapper();
        ProductCategory productCategory = mapper.toProductCategory(productCategorySimpleDto);
        return mapper.toProductCategorySimpleDto(productCategoryRepository.save(productCategory));
    }

    public ProductCategorySimpleDto deleteProductCategory(UUID id) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        ProductCategorySimpleTranslatorMapper mapper = new ProductCategorySimpleTranslatorMapper();
        if (productCategory != null) {
            productCategoryRepository.delete(productCategory.get());
        } else {
            throw new ProductCategoryNotFoundException(id);

        }
        return mapper.toProductCategorySimpleDto(productCategory.get());
    }

    public List<ProductCategorySimpleDto> getAllProductCategories() {
        List<ProductCategorySimpleDto> productCategorySimpleDtoList = new ArrayList<ProductCategorySimpleDto>();
        ProductCategorySimpleTranslatorMapper mapper = new ProductCategorySimpleTranslatorMapper();
        productCategoryRepository.findAll().forEach(productCategory -> productCategorySimpleDtoList.add(mapper.toProductCategorySimpleDto(productCategory)));
        return productCategorySimpleDtoList;
    }

    public ProductCategory findProductCategoryById(UUID id) throws Exception {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if (productCategory.isPresent())
            return productCategory.get();
        else
            throw new Exception("Could not find product category!");
    }

}
