package ro.msg.learning.shop.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "location")
public class Location extends EntityId {
    private String name;
    private String country;
    private String city;
    private String county;
    private String address;

}
