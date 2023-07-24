package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.ProductCategory;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    Optional<ProductCategory> findById(UUID id);

    @Query(value = "SELECT COUNT(*) > 0 " +
            "FROM product_category " +
            "WHERE name = :name " +
            "AND description = :description", nativeQuery = true)
    boolean existsByAttributes(@Param("name") String name, @Param("description") String description);
}

