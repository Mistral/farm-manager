package pro.farmmanager.resources;

import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ResourceFacade {

    private ResourceManager resourceManager;

    ResourceFacade(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public Either<ResourceError, UUID> createResource(String name, String description, ResourceType type) {
        return resourceManager.createResource(name, description, type);
    }

    public Either<ResourceError, UUID> createResourceWithVariant(String name, String description, ResourceType type, List<ResourceVariantCreateRequest> variants) {
        return resourceManager.createResourceWithVariant(name, description, type, variants);
    }

    public Either<ResourceError, UUID> addVariantsToResource(UUID resourceId, List<ResourceVariantCreateRequest> variants) {
        return resourceManager.addVariantsToResource(resourceId, variants);
    }

    public Option<ResourceDto> findResourceById(UUID resourceId) {
        return resourceManager.findResourceById(resourceId)
                              .map(Resource::toDto);
    }

    public Option<ResourceVariantDto> findResourceVariantById(UUID variantId) {
        return resourceManager.findResourceVariantById(variantId)
                              .map(ResourceVariant::toDto);
    }

    public List<ResourceDto> getResources() {
        return resourceManager.getResources()
                              .stream()
                              .map(Resource::toDto)
                              .collect(Collectors.toList());
    }

}
