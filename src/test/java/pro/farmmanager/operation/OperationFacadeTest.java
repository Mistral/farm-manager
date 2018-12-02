package pro.farmmanager.operation;

import org.junit.Before;
import org.junit.Test;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.infrastructure.InMemorySystem;
import pro.farmmanager.shared_kernel.Money;
import pro.farmmanager.user.UserFacade;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class OperationFacadeTest {

    private OperationFacade operationFacade;

    private FarmlandFacade farmlandFacade;

    private UserFacade userFacade;

    private InMemorySystem system;

    @Before
    public void setUp() {
        system = new InMemorySystem();
        operationFacade = system.getOperationFacade();
        farmlandFacade = system.getFarmlandFacade();
        userFacade = system.getUserFacade();
    }

    private UUID createFarmland() {
        return farmlandFacade.createFarmland("Test Farmland", 2.0f, userFacade.getAuthorizedUser()).get();
    }

    @Test
    public void shouldCreateOperationInFarmland() {
        UUID farmlandId = createFarmland();
        UUID operationId = operationFacade.createOperation(farmlandId, OperationType.DISKING, new Money(25)).get();
        assertNotNull(operationId);
    }

    @Test
    public void shouldNotCreateOperationWithInvalidFarmlandId() {
        UUID farmlandId = UUID.randomUUID();
        OperationError operationError = operationFacade.createOperation(farmlandId, OperationType.DISKING, new Money(25)).left().get();
        assertNotNull(operationError);
    }

    @Test
    public void shouldGetOperationDetails() {
        UUID farmlandId = createFarmland();
        UUID operationId = operationFacade.createOperation(farmlandId, OperationType.DISKING, new Money(25)).get();

        OperationDto operationDto = operationFacade.getOperationById(operationId).get();

        assertEquals(OperationType.DISKING, operationDto.getType());
        assertEquals(operationDto.getUnitCost(), new Money(25));
    }

    @Test
    public void shouldGetOperations() {
        UUID farmlandId = createFarmland();
        UUID operationId1 = operationFacade.createOperation(farmlandId, OperationType.DISKING, new Money(25)).get();
        UUID operationId2 = operationFacade.createOperation(farmlandId, OperationType.CULTIVATION, new Money(35)).get();

        List<OperationDto> operations = operationFacade.getOperations(farmlandId);

        assertEquals(2, operations.size());
    }

    @Test
    public void shouldCalculateOperationCost() {
        UUID farmlandId = createFarmland();
        UUID operationId = operationFacade.createOperation(farmlandId, OperationType.DISKING, new Money(25)).get();

        OperationDto operation = operationFacade.getOperationById(operationId).get();

        assertEquals(operation.getUnitCost(), new Money(25));
        assertEquals(operation.getTotalCost(), new Money(25).multiplyBy(2));
        assertEquals(operation.getOperationCost(), new Money(25).multiplyBy(2));
    }

}
