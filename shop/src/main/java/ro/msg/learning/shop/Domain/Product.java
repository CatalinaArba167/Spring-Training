package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "product")
public class Product extends EntityId {
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String supplier;
    @Column(name="image_url")
    private String imageUrl;

    @ManyToOne
    @JdbcTypeCode(SqlTypes.UUID)
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;

}
