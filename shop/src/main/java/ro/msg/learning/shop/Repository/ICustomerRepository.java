package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}