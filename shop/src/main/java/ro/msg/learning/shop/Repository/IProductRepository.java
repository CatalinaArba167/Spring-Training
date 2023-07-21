package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findById(UUID id);

    @Query(value = "SELECT COUNT(*) > 0 " +
            "FROM product " +
            "WHERE name = :productName " +
            "AND description = :productDescription " +
            "AND price = :productPrice " +
            "AND weight = :productWeight " +
            "AND supplier = :productSupplier " +
            "AND image_url = :productImageUrl " +
            "AND category_id = :productCategoryId ", nativeQuery = true)
    boolean existsByAttributes(@Param("productName") String productName,
                               @Param("productDescription") String productDescription,
                               @Param("productPrice") BigDecimal productPrice,
                               @Param("productWeight") Double productWeight,
                               @Param("productSupplier") String productSupplier,
                               @Param("productImageUrl") String productImageUrl,
                               @Param("productCategoryId") UUID productCategoryId);
}