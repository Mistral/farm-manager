package pro.farmmanager.operation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface SpringOperationRepository extends JpaRepository<Operation, UUID> {

    List<Operation> findByFarmlandId(UUID farmlandId);

}
