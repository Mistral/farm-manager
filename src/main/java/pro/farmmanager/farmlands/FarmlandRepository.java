package pro.farmmanager.farmlands;

import pro.farmmanager.infrastructure.Repository;

import java.util.List;
import java.util.UUID;

interface FarmlandRepository extends Repository<Farmland> {

    List<Farmland> findByOwnerId(UUID ownerId);

}
