package pro.farmmanager.operation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface ResourceSpringRepository extends JpaRepository<Resource, UUID> {

    Optional<Resource> findByName(String name);

}
