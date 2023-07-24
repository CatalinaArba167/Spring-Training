package ro.msg.learning.shop.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

@Entity
@NoArgsConstructor
@Data
@Table(name = "location")
public class Location extends EntityId {
    private String name;
    private String country;
    private String city;
    private String county;
    private String address;

}
