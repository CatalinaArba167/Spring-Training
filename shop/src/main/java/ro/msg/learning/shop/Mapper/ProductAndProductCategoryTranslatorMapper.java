package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;

@Component
public class ProductAndProductCategoryTranslatorMapper {
    public static ProductAndProductCategoryDto toDto(Product product) {
        return ProductAndProductCategoryDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .productWeight(product.getWeight())
                .productSupplier(product.getSupplier())
                .productImageUrl(product.getImageUrl())
                .productCategoryId(product.getProductCategory().getId())
                .productCategoryName(product.getProductCategory().getName())
                .productCategoryDescription(product.getProductCategory().getDescription())
                .build();
    }

    public static Product toEntity(ProductAndProductCategoryDto productAndProductCategoryDto, ProductCategory productCategory) {
        return Product.builder()
                .name(productAndProductCategoryDto.getProductName())
                .description(productAndProductCategoryDto.getProductDescription())
                .price(productAndProductCategoryDto.getProductPrice())
                .weight(productAndProductCategoryDto.getProductWeight())
                .supplier(productAndProductCategoryDto.getProductSupplier())
                .imageUrl(productAndProductCategoryDto.getProductImageUrl())
                .productCategory(productCategory)
                .build();
    }

}
