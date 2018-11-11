package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class ResourceVariant extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

}
