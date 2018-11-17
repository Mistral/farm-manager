package pro.farmmanager.farmlands;

import org.junit.Before;
import org.junit.Test;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.farmlands.exceptions.FarmlandInvalidParams;
import pro.farmmanager.infrastructure.InMemorySystem;

import java.util.UUID;

import static org.junit.Assert.*;

public class FarmlandFacadeTest {

    private FarmlandFacade farmlandFacade;

    private InMemorySystem system;

    @Before
    public void setUp() throws Exception {
        system = new InMemorySystem();
        farmlandFacade = system.getFarmlandFacade();
    }

    @Test
    public void shouldCreateFarmland() {
        UUID farmlandId = farmlandFacade.createFarmland("Custom Name", 2.75f);
        assertNotNull(farmlandId);
    }

    @Test(expected = FarmlandInvalidParams.class)
    public void shouldNotCreateFarmlandWithoutNameOrArea() {
        UUID farmlandId = farmlandFacade.createFarmland(null, null);
        assertNull(farmlandId);
    }

    @Test
    public void getFarmlandWithDetails() {
        //        FarmlandDto farmlandDto = farmlandFacade.getFarmlandById(customFarmlandId);
        //
        //        assertEquals("Custom Farmland", farmlandDto.getName());
        //        assertEquals(1.25, farmlandDto.getArea());
    }

}
