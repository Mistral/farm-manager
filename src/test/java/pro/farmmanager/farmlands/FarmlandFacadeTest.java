package pro.farmmanager.farmlands;

import org.junit.Before;
import org.junit.Test;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.farmlands.exceptions.FarmlandInvalidParams;
import pro.farmmanager.infrastructure.InMemorySystem;

import java.util.UUID;

import static org.junit.Assert.*;

public class FarmlandFacadeTest {

    private final static Float FARMLAND_AREA = 2.25f;

    private final static String FARMLAND_NAME = "Farmland Custom Name";

    private FarmlandFacade farmlandFacade;

    private InMemorySystem system;

    @Before
    public void setUp() throws Exception {
        system = new InMemorySystem();
        farmlandFacade = system.getFarmlandFacade();
    }

    @Test
    public void shouldCreateFarmland() {
        UUID farmlandId = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA);
        assertNotNull(farmlandId);
    }

    @Test(expected = FarmlandInvalidParams.class)
    public void shouldNotCreateFarmlandWithoutNameOrArea() {
        UUID farmlandId = farmlandFacade.createFarmland(null, null);
        assertNull(farmlandId);
    }

    @Test
    public void shouldGetFarmlandWithDetails() {
        UUID farmlandId = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA);

        FarmlandDto farmlandDto = farmlandFacade.getFarmlandById(farmlandId);

        assertEquals(FARMLAND_NAME, farmlandDto.getName());
        assertEquals(FARMLAND_AREA, farmlandDto.getArea(), 0.0);
    }

    @Test
    public void shouldArchiveFarmland() {
        UUID farmlandId = farmlandFacade.createFarmland(FARMLAND_NAME, FARMLAND_AREA);

        farmlandFacade.archiveFarmland(farmlandId);

        FarmlandDto farmlandDto = farmlandFacade.getFarmlandById(farmlandId);

        assertTrue(farmlandDto.isArchived());
    }

}
