package pro.farmmanager.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.infrastructure.InMemoryRepository;
import pro.farmmanager.infrastructure.Repository;
import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

@Configuration
public class ResourceConfiguration {

    @Bean
    public ResourceFacade resourceFacade(ResourceSpringRepository springRepository, ResourceVariantSpringRepository resourceVariantSpringRepository) {
        ResourceRepository repository = new ResourceSpringRepositoryAdapter(springRepository);
        Repository<ResourceVariant> resourceVariantRepository = new SpringRepositoryAdapter<>(resourceVariantSpringRepository);
        ResourceManager resourceManager = new ResourceManager(repository, resourceVariantRepository);
        return new ResourceFacade(resourceManager);
    }

    public ResourceFacade resourceFacade() {
        ResourceRepository repository = new ResourceInMemoryRepository();
        Repository<ResourceVariant> resourceVariantRepository = new InMemoryRepository<>();
        ResourceManager resourceManager = new ResourceManager(repository, resourceVariantRepository);
        return new ResourceFacade(resourceManager);
    }

}
