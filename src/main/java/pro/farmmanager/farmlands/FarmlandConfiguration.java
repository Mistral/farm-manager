package pro.farmmanager.farmlands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.user.UserFacade;

@Configuration
public class FarmlandConfiguration {

    @Bean
    public FarmlandFacade farmlandFacade(SpringFarmlandRepository springFarmlandRepository, UserFacade userFacade) {
        FarmlandRepository farmlandRepository = new FarmlandSpringRepositoryAdapter(springFarmlandRepository);
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager);
    }

    public FarmlandFacade farmlandFacade() {
        FarmlandRepository farmlandRepository = new FarmlandInMemoryRepository();
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager);
    }

}
