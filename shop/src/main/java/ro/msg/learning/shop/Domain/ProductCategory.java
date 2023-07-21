package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategory extends EntityId {
    private String name;
    private String description;
}
