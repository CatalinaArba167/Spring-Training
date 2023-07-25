package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
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
