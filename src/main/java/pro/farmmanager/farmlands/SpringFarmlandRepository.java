package pro.farmmanager.farmlands;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface SpringFarmlandRepository extends JpaRepository<Farmland, UUID> {

}
