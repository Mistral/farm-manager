package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
class Resource extends BaseEntity {

    public enum Type {
        CHEMISTRY,
        FERTILIZERS,
        SEEDS
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "resource_id")
    private List<ResourceVariant> variants;

    @Enumerated
    private Type type;

}
