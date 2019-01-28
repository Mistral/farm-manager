package pro.farmmanager.operation;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.infrastructure.Repository;
import pro.farmmanager.operation.dto.NewResourceVariantDto;

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

    Either<ResourceError, UUID> createResourceWithVariant(String name, String description, ResourceType type, List<NewResourceVariantDto> variants) {
        return resourceNameAvailable(name)
            .map(s -> {
                Resource resource = Resource.createWithVariants(name, description, type, createResourceVariantsOf(variants));
                resourceRepository.save(resource);
                return resource.getId();
            })
            .toEither(ResourceError.RESOURCE_EXIST);
    }

    private List<ResourceVariant> createResourceVariantsOf(List<NewResourceVariantDto> variants) {
        return variants.stream()
                       .map(ResourceVariant::createOf)
                       .collect(Collectors.toList());
    }

    Either<ResourceError, String> resourceNameAvailable(String name) {
        return resourceRepository.findByName(name)
                                 .map(Resource::getName)
                                 .toEither(ResourceError.RESOURCE_EXIST);
    }

    Either<ResourceError, UUID> addVariantsToResource(UUID resourceId, List<NewResourceVariantDto> variants) {
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

}
