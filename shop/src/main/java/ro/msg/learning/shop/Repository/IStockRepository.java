package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.BaseClassesForIds.StockId;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;

import java.util.List;
import java.util.UUID;


@Repository
public interface IStockRepository extends JpaRepository<Stock, StockId> {
    Stock findByProductIdAndLocationId(UUID product, UUID location);

    List<Stock> findByProduct(Product product);

    @Query("SELECT location FROM Stock WHERE product.id IN :products " +
            "GROUP BY location " +
            "HAVING COUNT(DISTINCT product) = :productCount")
    List<Location> findLocationsWithAllProducts(@Param("products") List<UUID> products,
                                                @Param("productCount") long productCount);
}