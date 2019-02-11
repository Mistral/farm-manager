package pro.farmmanager.resources;

import java.util.List;
import java.util.UUID;

public class ResourceDto {

    private final UUID id;

    private final String name;

    private final String description;

    private final ResourceType type;

    private final List<ResourceVariantDto> variants;

    public ResourceDto(UUID id, String name, String description, ResourceType type, List<ResourceVariantDto> variantDtos) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.variants = variantDtos;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ResourceType getType() {
        return type;
    }

    public List<ResourceVariantDto> getVariants() {
        return variants;
    }

}
