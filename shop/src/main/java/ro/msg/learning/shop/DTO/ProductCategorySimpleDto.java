package ro.msg.learning.shop.DTO;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategorySimpleDto {
    private UUID id;
    private String name;
    private String description;
}
