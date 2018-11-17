package pro.farmmanager.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.farmmanager.infrastructure.InMemoryRepository;
import pro.farmmanager.infrastructure.Repository;
import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

@Configuration
public class UserConfiguration {

    @Bean
    public UserFacade userFacade(SpringUserRepository springUserRepository) {
        Repository<User> repository = new SpringRepositoryAdapter<>(springUserRepository);
        UserManager userManager = new UserManager(repository);
        return new UserFacade(userManager);
    }

    public UserFacade userFacade() {
        Repository<User> repository = new InMemoryRepository<>();
        UserManager userManager = new UserManager(repository);
        return new UserFacade(userManager);
    }

}
