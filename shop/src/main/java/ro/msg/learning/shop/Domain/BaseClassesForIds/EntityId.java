package ro.msg.learning.shop.Domain.BaseClassesForIds;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//@Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together
@MappedSuperclass
@Getter
@Setter
public abstract class EntityId {
    @Id
    @GeneratedValue
    private UUID id;
}


