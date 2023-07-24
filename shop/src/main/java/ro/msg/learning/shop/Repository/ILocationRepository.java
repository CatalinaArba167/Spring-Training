package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.Domain.Location;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ILocationRepository extends JpaRepository<Location, UUID> {
    Location findByName(String name);

    Optional<Location> findById(UUID id);
}