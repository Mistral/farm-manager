package pro.farmmanager.shared_kernel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    String uuid = UUID.randomUUID().toString();

    @Version
    Integer version;

    public Long getId() {
        return this.id;
    }

    public int hashCode() {
        return Objects.hash(uuid);
    }

    public boolean equals(Object that) {
        return this == that || that instanceof BaseEntity
            && Objects.equals(uuid, ((BaseEntity) that).uuid);
    }

}
