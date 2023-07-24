package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.ProductAndProductCategoryDto;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;

@Component
public class ProductAndProductCategoryTranslatorMapper {
    public ProductAndProductCategoryDto toProductAndProductCategoryDto(Product product) {
        ProductAndProductCategoryDto productAndProductCategoryDto = new ProductAndProductCategoryDto();
        //Product attributes
        productAndProductCategoryDto.setProductId(product.getId());
        productAndProductCategoryDto.setProductName(product.getName());
        productAndProductCategoryDto.setProductDescription(product.getDescription());
        productAndProductCategoryDto.setProductPrice(product.getPrice());
        productAndProductCategoryDto.setProductWeight(product.getWeight());
        productAndProductCategoryDto.setProductSupplier(product.getSupplier());
        productAndProductCategoryDto.setProductImageUrl(product.getImageUrl());
        //ProductCategory attributes
        productAndProductCategoryDto.setProductCategoryId(product.getProductCategory().getId());
        productAndProductCategoryDto.setProductCategoryName(product.getProductCategory().getName());
        productAndProductCategoryDto.setProductCategoryDescription(product.getProductCategory().getDescription());
        return productAndProductCategoryDto;
    }

    public Product toProduct(ProductAndProductCategoryDto productAndProductCategoryDto, ProductCategory productCategory) {
        Product product = new Product();
        product.setId(productAndProductCategoryDto.getProductId());
        product.setName(productAndProductCategoryDto.getProductName());
        product.setDescription(productAndProductCategoryDto.getProductDescription());
        product.setPrice(productAndProductCategoryDto.getProductPrice());
        product.setWeight(productAndProductCategoryDto.getProductWeight());
        product.setSupplier(productAndProductCategoryDto.getProductSupplier());
        product.setImageUrl(productAndProductCategoryDto.getProductImageUrl());
        product.setProductCategory(productCategory);
        return product;
    }

}
