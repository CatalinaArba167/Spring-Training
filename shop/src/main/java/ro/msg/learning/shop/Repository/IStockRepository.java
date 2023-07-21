package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.Location;
import ro.msg.learning.shop.Domain.Product;
import ro.msg.learning.shop.Domain.Stock;

import java.util.List;


@Repository
public interface IStockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductAndLocation(Product product, Location location);
    List<Stock> findByProduct(Product product);
}