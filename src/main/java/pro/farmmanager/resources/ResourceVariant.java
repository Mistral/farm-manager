package pro.farmmanager.resources;

import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.Entity;

@Entity
class ResourceVariant extends BaseEntity {

    private String name;

    private String description;

    static ResourceVariant createOf(ResourceVariantCreateRequest resourceVariantDto) {
        return new ResourceVariant(resourceVariantDto.getName(), resourceVariantDto.getDescription());
    }

    static ResourceVariant from(ResourceVariantDto variant) {
        return new ResourceVariant(variant.getName(), variant.getDescription());
    }

    private ResourceVariant() {

    }

    private ResourceVariant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    ResourceVariantDto toDto() {
        return new ResourceVariantDto(getId(), name, description);
    }

}
