package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
class Resource extends BaseEntity {

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "resource_id")
    private List<ResourceVariant> variants;

    @Enumerated
    private ResourceType type;

    static Resource from(ResourceDto resource) {
        return null;
    }

}
