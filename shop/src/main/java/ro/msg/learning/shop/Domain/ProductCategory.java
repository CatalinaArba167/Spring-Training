package ro.msg.learning.shop.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategory extends EntityId {
    private String name;
    private String description;
}
