package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.Entity;

@Entity
class ResourceVariant extends BaseEntity {

    private String name;

    private String description;

}
