package pro.farmmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class FarmManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmManagerApplication.class, args);
    }

}
