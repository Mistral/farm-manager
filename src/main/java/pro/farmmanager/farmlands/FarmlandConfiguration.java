package pro.farmmanager.farmlands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.infrastructure.InMemoryRepository;
import pro.farmmanager.infrastructure.Repository;
import pro.farmmanager.infrastructure.SpringRepositoryAdapter;
import pro.farmmanager.user.UserFacade;

@Configuration
public class FarmlandConfiguration {

    @Bean
    public FarmlandFacade farmlandFacade(SpringFarmlandRepository springFarmlandRepository, UserFacade userFacade) {
        Repository<Farmland> farmlandRepository = new SpringRepositoryAdapter(springFarmlandRepository);
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager, userFacade);
    }

    public FarmlandFacade farmlandFacade(UserFacade userFacade) {
        Repository<Farmland> farmlandRepository = new InMemoryRepository<>();
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager, userFacade);
    }

}
