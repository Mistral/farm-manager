package pro.farmmanager.operation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OperationConfiguration {

    @Bean
    public OperationFacade operationFacade(OperationRepository operationRepository) {
        OperationManager operationManager = new OperationManager(operationRepository);
        return new OperationFacade(operationManager);
    }

}
