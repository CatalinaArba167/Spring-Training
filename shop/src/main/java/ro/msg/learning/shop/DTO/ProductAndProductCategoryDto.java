package ro.msg.learning.shop.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    //The location
    private UUID locationId;
    //The stock quantity
    private Integer stockQuantity;
}
