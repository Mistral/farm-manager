package pro.farmmanager.resources.dto;

import pro.farmmanager.resources.ResourceType;
import pro.farmmanager.resources.ResourceVariantCreateRequest;

import java.util.List;

public class ResourceCreateRequest {

    private String name;

    private String description;

    private ResourceType type;

    private List<ResourceVariantCreateRequest> variants;

    ResourceCreateRequest(String name, String description, ResourceType type, List<ResourceVariantCreateRequest> variants) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.variants = variants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public List<ResourceVariantCreateRequest> getVariants() {
        return variants;
    }

    public void setVariants(List<ResourceVariantCreateRequest> variants) {
        this.variants = variants;
    }

}
