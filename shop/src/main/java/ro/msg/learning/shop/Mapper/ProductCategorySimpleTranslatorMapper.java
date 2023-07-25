package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.ProductCategorySimpleDto;
import ro.msg.learning.shop.Domain.ProductCategory;

@Component
public class ProductCategorySimpleTranslatorMapper {
    public static ProductCategorySimpleDto toDto(ProductCategory productCategory) {
        return ProductCategorySimpleDto.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();
    }

    public static ProductCategory toEntity(ProductCategorySimpleDto productCategorySimpleDto) {
        return ProductCategory.builder()
                .name(productCategorySimpleDto.getName())
                .description(productCategorySimpleDto.getDescription())
                .build();
    }
}