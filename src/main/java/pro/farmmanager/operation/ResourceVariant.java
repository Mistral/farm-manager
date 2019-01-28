package pro.farmmanager.operation;

import pro.farmmanager.operation.dto.NewResourceVariantDto;
import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.Entity;

@Entity
class ResourceVariant extends BaseEntity {

    private String name;

    private String description;

    static ResourceVariant createOf(NewResourceVariantDto resourceVariantDto) {
        return new ResourceVariant(resourceVariantDto.getName(), resourceVariantDto.getDescription());
    }

    private ResourceVariant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    static ResourceVariant from(ResourceVariantDto variant) {
    }

}
