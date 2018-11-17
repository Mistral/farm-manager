package pro.farmmanager.shared_kernel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    UUID id = UUID.randomUUID();

    @Version
    Integer version;

    public UUID getId() {
        return this.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean equals(Object that) {
        return this == that || that instanceof BaseEntity
            && Objects.equals(id, ((BaseEntity) that).id);
    }

}
