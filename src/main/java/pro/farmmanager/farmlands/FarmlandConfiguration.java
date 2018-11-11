package pro.farmmanager.farmlands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FarmlandConfiguration {

    @Bean
    FarmlandFacade farmlandFacade(FarmlandRepository farmlandRepository) {
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager);
    }

}
