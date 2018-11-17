package pro.farmmanager.farmlands;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.infrastructure.InMemoryRepository;
import pro.farmmanager.infrastructure.Repository;
import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

@Configuration
public class FarmlandConfiguration {

    @Bean
    public FarmlandFacade farmlandFacade(SpringFarmlandRepository springFarmlandRepository) {
        Repository<Farmland> farmlandRepository = new SpringRepositoryAdapter(springFarmlandRepository);
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager);
    }

    public FarmlandFacade farmlandFacade() {
        Repository<Farmland> farmlandRepository = new InMemoryRepository<>();
        FarmlandManager farmlandManager = new FarmlandManager(farmlandRepository);
        return new FarmlandFacade(farmlandManager);
    }

}
