package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.ProductCategorySimpleDto;
import ro.msg.learning.shop.Domain.ProductCategory;

@Component
public class ProductCategorySimpleTranslatorMapper {
    public ProductCategorySimpleDto toProductCategorySimpleDto(ProductCategory productCategory) {
        ProductCategorySimpleDto productCategorySimpleDto= new ProductCategorySimpleDto();
        productCategorySimpleDto.setId(productCategory.getId());
        productCategorySimpleDto.setName(productCategory.getName());
        productCategorySimpleDto.setDescription(productCategory.getDescription());
        return productCategorySimpleDto;
    }

    public ProductCategory toProductCategory(ProductCategorySimpleDto productCategorySimpleDto) {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setId(productCategorySimpleDto.getId());
        productCategory.setName(productCategorySimpleDto.getName());
        productCategory.setDescription(productCategorySimpleDto.getDescription());
        return productCategory;
    }
}