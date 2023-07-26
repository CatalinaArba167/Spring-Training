package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.Orders;

import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, UUID> {
}