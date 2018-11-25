package pro.farmmanager.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.farmlands.FarmlandFacade;

@Configuration
public class OperationConfiguration {

    @Bean
    public OperationFacade operationFacade(SpringOperationRepository springOperationRepository, FarmlandFacade farmlandFacade) {
        OperationRepository repository = new OperationSpringRepositoryAdapter(springOperationRepository);
        OperationManager operationManager = new OperationManager(repository, farmlandFacade);
        return new OperationFacade(operationManager);
    }

    public OperationFacade operationFacade(FarmlandFacade farmlandFacade) {
        OperationRepository repository = new OperationInMemoryRepository();
        OperationManager operationManager = new OperationManager(repository, farmlandFacade);
        return new OperationFacade(operationManager);
    }

}
