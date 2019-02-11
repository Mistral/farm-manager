package pro.farmmanager.resources;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.infrastructure.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class ResourceManager {

    private final ResourceRepository resourceRepository;

    private final Repository<ResourceVariant> resourceVariantRepository;

    ResourceManager(ResourceRepository resourceRepository, Repository<ResourceVariant> resourceVariantRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceVariantRepository = resourceVariantRepository;
    }

    Either<ResourceError, UUID> createResource(String name, String description, ResourceType type) {
        return resourceNameAvailable(name)
            .map(s -> {
                Resource resource = Resource.create(name, description, type);
                resourceRepository.save(resource);
                return resource.getId();
            })
            .toEither(ResourceError.RESOURCE_EXIST);
    }

    Either<ResourceError, UUID> createResourceWithVariant(String name, String description, ResourceType type, List<ResourceVariantCreateRequest> variants) {
        return resourceNameAvailable(name)
            .map(s -> {
                Resource resource = Resource.createWithVariants(name, description, type, createResourceVariantsOf(variants));
                resourceRepository.save(resource);
                return resource.getId();
            })
            .toEither(ResourceError.RESOURCE_EXIST);
    }

    private List<ResourceVariant> createResourceVariantsOf(List<ResourceVariantCreateRequest> variants) {
        return variants.stream()
                       .map(ResourceVariant::createOf)
                       .collect(Collectors.toList());
    }

    private Either<ResourceError, String> resourceNameAvailable(String name) {
        return (resourceRepository.findByName(name).isDefined()) ? Either.left(ResourceError.RESOURCE_EXIST) : Either.right(name);
    }

    Either<ResourceError, UUID> addVariantsToResource(UUID resourceId, List<ResourceVariantCreateRequest> variants) {
        return resourceRepository.findById(resourceId)
                                 .map(resource -> {
                                     resource.addVariants(createResourceVariantsOf(variants));
                                     resourceRepository.save(resource);
                                     return resource.getId();
                                 })
                                 .toEither(ResourceError.RESOURCE_NOT_EXIST);
    }

    Option<Resource> findResourceById(UUID resourceId) {
        return resourceRepository.findById(resourceId);
    }

    Option<ResourceVariant> findResourceVariantById(UUID variantId) {
        return resourceVariantRepository.findById(variantId);
    }

    List<Resource> getResources() {
        return resourceRepository.findAll();
    }

}
