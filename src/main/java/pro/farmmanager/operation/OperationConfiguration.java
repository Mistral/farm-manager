package pro.farmmanager.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationConfiguration {

    @Bean
    public OperationFacade operationFacade(SpringOperationRepository springOperationRepository) {
        OperationRepository repository = new OperationSpringRepositoryAdapter(springOperationRepository);
        OperationManager operationManager = new OperationManager(repository);
        return new OperationFacade(operationManager);
    }

    public OperationFacade operationFacade() {
        OperationRepository repository = new OperationInMemoryRepository();
        OperationManager operationManager = new OperationManager(repository);
        return new OperationFacade(operationManager);
    }

}
