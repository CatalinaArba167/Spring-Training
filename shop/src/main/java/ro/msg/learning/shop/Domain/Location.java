package ro.msg.learning.shop.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.Domain.BaseClassesForIds.EntityId;

import java.util.List;
import java.util.UUID;

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
