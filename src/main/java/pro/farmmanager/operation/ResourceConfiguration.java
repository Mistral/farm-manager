package pro.farmmanager.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceConfiguration {

    @Bean
    public ResourceFacade resourceFacade() {
        ResourceManager resourceManager = new ResourceManager();
        return new ResourceFacade(resourceManager);
    }

}
