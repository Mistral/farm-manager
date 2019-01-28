package pro.farmmanager.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceConfiguration {

    @Bean
    public ResourceFacade resourceFacade(ResourceSpringRepository springRepository, ResourceVariantSpringRepository resourceVariantSpringRepository) {
        ResourceRepository repository = new ResourceSpringRepositoryAdapter(springRepository);
        ResourceManager resourceManager = new ResourceManager(repository);
        return new ResourceFacade(resourceManager);
    }

    public ResourceFacade resourceFacade() {
        ResourceRepository repository = new ResourceInMemoryRepository();
        ResourceManager resourceManager = new ResourceManager(repository);
        return new ResourceFacade(resourceManager);
    }

}
