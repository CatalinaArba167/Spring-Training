package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.OrderDetail;
import ro.msg.learning.shop.Domain.Orders;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long> {
}