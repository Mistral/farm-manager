package pro.farmmanager.operation;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.operation.dto.NewResourceVariantDto;

import java.util.List;
import java.util.UUID;

public class ResourceFacade {

    private ResourceManager resourceManager;

    ResourceFacade(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public Either<ResourceError, UUID> createResource(String name, String description, ResourceType type) {
        return resourceManager.createResource(name, description, type);
    }

    public Either<ResourceError, UUID> createResourceWithVariant(String name, String description, ResourceType type, List<NewResourceVariantDto> variants) {
        return resourceManager.createResourceWithVariant(name, description, type, variants);
    }

    public Either<ResourceError, UUID> addVariantsToResource(UUID resourceId, List<NewResourceVariantDto> variants) {
        return resourceManager.addVariantsToResource(resourceId, variants);
    }

    public Option<Resource> findResourceById(UUID resourceId) {
        return resourceManager.findResourceById(resourceId);
    }

    public Option<ResourceVariant> findResourceVariantById(UUID variantId) {
        return resourceManager.findResourceVariantById(variantId);
    }

}
