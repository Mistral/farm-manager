package pro.farmmanager.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ResourceVariantSpringRepository extends JpaRepository<ResourceVariant, UUID> {

}
