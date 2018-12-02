package pro.farmmanager.farmlands;

import org.junit.Before;
import org.junit.Test;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.infrastructure.InMemorySystem;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class FarmlandFacadeTest {

    private final static Float FARMLAND_AREA = 2.25f;

    private final static String FARMLAND_NAME = "Farmland Custom Name";

    private FarmlandFacade farmlandFacade;

    private UUID ownerId;

    private InMemorySystem system;

    @Before
    public void setUp() throws Exception {
        system = new InMemorySystem();
        farmlandFacade = system.getFarmlandFacade();
        ownerId = system.getCustomUserId();
    }

    @Test
    public void shouldCreateFarmland() {
        UUID farmlandId = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA, ownerId).get();
        assertNotNull(farmlandId);
    }

    @Test
    public void shouldNotCreateFarmlandWithoutNameOrArea() {
        FarmlandError farmlandError = farmlandFacade.createFarmland(null, null, ownerId).left().get();
        assertNotNull(farmlandError);
    }

    @Test
    public void shouldGetFarmlandWithDetails() {
        UUID farmlandId = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA, ownerId).get();

        FarmlandDto farmlandDto = farmlandFacade.getFarmlandById(farmlandId).get();

        assertEquals(FARMLAND_NAME, farmlandDto.getName());
        assertEquals(FARMLAND_AREA, farmlandDto.getArea(), 0.0);
    }

    @Test
    public void shouldArchiveFarmland() {
        UUID farmlandId = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA, ownerId).get();

        farmlandFacade.archiveFarmland(farmlandId);

        FarmlandDto farmlandDto = farmlandFacade.getFarmlandById(farmlandId).get();

        assertTrue(farmlandDto.isArchived());
    }

    @Test
    public void shouldGetFarmlands() {
        UUID farmlandId1 = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA, ownerId).get();
        UUID farmlandId2 = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA, ownerId).get();

        List<FarmlandDto> farmlands = farmlandFacade.getFarmlandsForUser(ownerId);

        assertEquals(2, farmlands.size());
    }

    @Test
    public void shouldNotGetOtherUserFarmlands() {
        UUID otherUserId = UUID.randomUUID();

        List<FarmlandDto> farmlands = farmlandFacade.getFarmlandsForUser(otherUserId);

        assertEquals(0, farmlands.size());
    }

}
