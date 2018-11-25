package pro.farmmanager.operation;

import org.junit.Before;
import org.junit.Test;
import pro.farmmanager.infrastructure.InMemorySystem;
import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

import static org.junit.Assert.*;

public class OperationFacadeTest {

    private OperationFacade operationFacade;
    private InMemorySystem system;

    @Before
    public void setUp() {
        system = new InMemorySystem();
        operationFacade = system.getOperationFacade();
    }

    public void shouldCreateOperationInFarmland() {
        UUID farmlandId = UUID.randomUUID();
        UUID operationId = operationFacade.createOperation(farmlandId, OperationType.DISKING, new Money(25));
        assertNotNull(operationId);
    }
}
