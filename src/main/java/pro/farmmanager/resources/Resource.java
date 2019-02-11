package pro.farmmanager.resources;

import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
class Resource extends BaseEntity {

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "resource_id")
    private List<ResourceVariant> variants = new ArrayList<>();

    @Enumerated
    private ResourceType type;

    static Resource from(ResourceDto resource) {
        return null;
    }

    static Resource create(String name, String description, ResourceType type) {
        return new Resource(name, description, type, null);
    }

    static Resource createWithVariants(String name, String description, ResourceType type, List<ResourceVariant> variants) {
        return new Resource(name, description, type, variants);
    }

    private Resource(String name, String description, ResourceType type, List<ResourceVariant> variants) {
        this.name = name;
        this.description = description;
        this.type = type;
        if (variants != null) {
            this.variants.addAll(variants);
        }
    }

    private Resource() {

    }

    ResourceDto toDto() {
        List<ResourceVariantDto> variantDtos = variants.stream().map(ResourceVariant::toDto).collect(Collectors.toList());
        return new ResourceDto(getId(), name, description, type, variantDtos);
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    List<ResourceVariant> getVariants() {
        return variants;
    }

    ResourceType getType() {
        return type;
    }

    void addVariants(List<ResourceVariant> variants) {
        this.variants.addAll(variants);
    }

}
