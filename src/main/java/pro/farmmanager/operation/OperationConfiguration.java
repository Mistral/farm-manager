package pro.farmmanager.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.resources.ResourceFacade;

@Configuration
public class OperationConfiguration {

    @Bean
    public OperationFacade operationFacade(OperationSpringRepository operationSpringRepository, FarmlandFacade farmlandFacade, ResourceFacade resourceFacade) {
        OperationRepository repository = new OperationSpringRepositoryAdapter(operationSpringRepository);
        OperationManager operationManager = new OperationManager(repository, farmlandFacade, resourceFacade);
        return new OperationFacade(operationManager);
    }

    public OperationFacade operationFacade(FarmlandFacade farmlandFacade, ResourceFacade resourceFacade) {
        OperationRepository repository = new OperationInMemoryRepository();
        OperationManager operationManager = new OperationManager(repository, farmlandFacade, resourceFacade);
        return new OperationFacade(operationManager);
    }

}
