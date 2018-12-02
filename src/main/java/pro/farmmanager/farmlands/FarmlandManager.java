package pro.farmmanager.farmlands;

import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.*;

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
            return name.length() >= 3;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }

    Option<Farmland> findFarmlandById(UUID farmlandId) {
        return Option.ofOptional(farmlandRepository.findById(farmlandId));
    }

    void archiveFarmland(UUID farmlandId) {
        findFarmlandById(farmlandId).peek(Farmland::archive);
    }

    List<Farmland> getFarmlandsForUser(UUID ownerId) {
        return farmlandRepository.findByOwnerId(ownerId);
    }

    List<Farmland> getFarmlands() {
        return farmlandRepository.findAll();
    }

}
