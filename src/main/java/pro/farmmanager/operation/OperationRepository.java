package pro.farmmanager.operation;

import pro.farmmanager.infrastructure.Repository;

import java.util.List;
import java.util.UUID;

interface OperationRepository extends Repository<Operation> {
    List<Operation> findByFarmlandId(UUID farmlandId);
}
