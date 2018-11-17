package pro.farmmanager.farmlands;

import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.farmlands.exceptions.FarmlandInvalidParams;
import pro.farmmanager.farmlands.exceptions.FarmlandNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

class FarmlandManager {

    private FarmlandRepository farmlandRepository;

    FarmlandManager(FarmlandRepository farmlandRepository) {
        this.farmlandRepository = farmlandRepository;
    }

    UUID createFarmland(String name, Float area, UUID ownerId) throws FarmlandInvalidParams {
        try {
            Objects.requireNonNull(name);
            Objects.requireNonNull(area);
            Objects.requireNonNull(ownerId);
        }
        catch (NullPointerException ex) {
            throw new FarmlandInvalidParams();
        }

        Farmland farmland = Farmland.create(name, area, ownerId);
        farmlandRepository.save(farmland);

        return farmland.getId();
    }

    FarmlandDto findFarmlandById(UUID farmlandId) throws FarmlandNotFoundException {
        return farmlandRepository.findById(farmlandId)
                                 .map(Farmland::toDto)
                                 .orElseThrow(FarmlandNotFoundException::new);
    }

    void archiveFarmland(UUID farmlandId) throws FarmlandNotFoundException {
        Farmland farmland = farmlandRepository.findById(farmlandId).orElseThrow(FarmlandNotFoundException::new);
        farmland.archive();
    }

    List<Farmland> getFarmlandsForUser(UUID ownerId) {
        return farmlandRepository.findByOwnerId(ownerId);
    }

}
