package ro.msg.learning.shop.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductCategorySimpleDto {
    private UUID id;
    private String name;
    private String description;
}
