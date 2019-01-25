package pro.farmmanager.operation;

import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public class ResourceFacade {

    private ResourceManager resourceManager;

    ResourceFacade(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public Either<ResourceError, UUID> createResource(String name, String description, ResourceType type) {

    }

    public Either<ResourceError, UUID> createResourceWithVariant(String name, String description, ResourceType type, List<ResourceVariantDto> variants) {

    }

}
