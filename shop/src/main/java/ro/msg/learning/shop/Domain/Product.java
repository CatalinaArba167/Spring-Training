package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.*;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "product")
public class Product extends EntityId {
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String supplier;
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

}
