package pro.farmmanager.farmlands;

import pro.farmmanager.infrastructure.Repository;

class FarmlandManager {

    private Repository<Farmland> farmlandRepository;

    FarmlandManager(Repository<Farmland> farmlandRepository) {
        this.farmlandRepository = farmlandRepository;
    }

}
