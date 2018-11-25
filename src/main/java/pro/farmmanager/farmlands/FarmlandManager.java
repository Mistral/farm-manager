package pro.farmmanager.farmlands;

import io.vavr.control.Either;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.farmlands.exceptions.FarmlandInvalidParams;
import pro.farmmanager.farmlands.exceptions.FarmlandNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

class FarmlandManager {

    private FarmlandRepository farmlandRepository;

    FarmlandManager(FarmlandRepository farmlandRepository) {
        this.farmlandRepository = farmlandRepository;
    }

    Either<FarmlandError, Farmland> createFarmland(String name, Float area, UUID ownerId) {
        if (validate(name, area, ownerId)) {
            Farmland farmland = Farmland.create(name, area, ownerId);
            farmlandRepository.save(farmland);
            return Either.right(farmland);
        }
        else {
            return Either.left(FarmlandError.INVALID_PARAMETERS);
        }
    }

    private boolean validate(String name, Float area, UUID ownerId) {
        try {
            Objects.requireNonNull(name);
            Objects.requireNonNull(area);
            Objects.requireNonNull(ownerId);
            return true;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }

    Optional<Farmland> findFarmlandById(UUID farmlandId) {
        return farmlandRepository.findById(farmlandId);
    }

    void archiveFarmland(UUID farmlandId) {
        findFarmlandById(farmlandId).ifPresent(Farmland::archive);
    }

    List<Farmland> getFarmlandsForUser(UUID ownerId) {
        return farmlandRepository.findByOwnerId(ownerId);
    }

}
