package ro.msg.learning.shop.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductAndProductCategoryDto {
    //Product attributes
    private UUID productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Double productWeight;
    private String productSupplier;
    private String productImageUrl;

    //ProductCategory attributes
    private UUID productCategoryId;
    private String productCategoryName;
    private String productCategoryDescription;
}
