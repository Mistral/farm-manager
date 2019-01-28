package pro.farmmanager.operation;

import org.junit.Before;
import org.junit.Test;
import pro.farmmanager.infrastructure.InMemorySystem;

import java.util.UUID;

import static org.junit.Assert.*;

public class ResourceFacadeTest {

    private InMemorySystem system;

    private ResourceFacade resourceFacade;

    @Before
    public void setUp() {
        system = new InMemorySystem();
        resourceFacade = system.getResourceFacade();
    }

    @Test
    public void shouldCreateResourceWithoutVariant() {
        UUID resourceId = resourceFacade.createResource("Test", "Dummu", ResourceType.CHEMISTRY).get();

        assertNotNull(resourceId);
    }

    @Test
    public void shouldNotCreateResourceWithExistingName() {
        UUID resourceId = resourceFacade.createResource("Test", "Dummy", ResourceType.CHEMISTRY).get();
        ResourceError resourceError = resourceFacade.createResource("Test", "Dummy", ResourceType.CHEMISTRY).getLeft();

        assertEquals(ResourceError.RESOURCE_EXIST, resourceError);
    }

}
