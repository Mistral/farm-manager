package pro.farmmanager.farmlands;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.farmlands.dto.FarmlandDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class FarmlandFacade {

    private FarmlandManager farmlandManager;

    FarmlandFacade(FarmlandManager farmlandManager) {
        this.farmlandManager = farmlandManager;
    }

    public Either<FarmlandError, UUID> createFarmland(String name, Float area, UUID ownerId) {
        return farmlandManager.createFarmland(name, area, ownerId).map(Farmland::getId);
    }

    public Option<FarmlandDto> getFarmlandById(UUID farmlandId) {
        return farmlandManager.findFarmlandById(farmlandId).map(Farmland::toDto);
    }

    public void archiveFarmland(UUID farmlandId) {
        farmlandManager.archiveFarmland(farmlandId);
    }

    public List<FarmlandDto> getFarmlandsForUser(UUID ownerId) {
        return farmlandManager.getFarmlandsForUser(ownerId)
                              .stream()
                              .map(Farmland::toDto)
                              .collect(Collectors.toList());
    }

    public List<FarmlandDto> getFarmlands() {
        return farmlandManager.getFarmlands()
                              .stream()
                              .map(Farmland::toDto)
                              .collect(Collectors.toList());
    }

}
