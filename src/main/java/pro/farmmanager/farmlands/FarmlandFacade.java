package pro.farmmanager.farmlands;

import pro.farmmanager.farmlands.dto.FarmlandDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FarmlandFacade {

    private FarmlandManager farmlandManager;

    FarmlandFacade(FarmlandManager farmlandManager) {
        this.farmlandManager = farmlandManager;
    }

    public UUID createFarmland(String name, Float area, UUID ownerId) {
        return farmlandManager.createFarmland(name, area, ownerId);
    }

    public FarmlandDto getFarmlandById(UUID farmlandId) {
        return farmlandManager.findFarmlandById(farmlandId);
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

}
