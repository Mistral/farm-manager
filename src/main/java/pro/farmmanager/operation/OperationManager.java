package pro.farmmanager.operation;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.operation.dto.NewOperationResourceDto;
import pro.farmmanager.resources.ResourceFacade;
import pro.farmmanager.resources.ResourceVariantDto;
import pro.farmmanager.shared_kernel.Money;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

class OperationManager {

    private final OperationRepository operationRepository;

    private final FarmlandFacade farmlandFacade;

    private final ResourceFacade resourceFacade;

    OperationManager(OperationRepository operationRepository, FarmlandFacade farmlandFacade, ResourceFacade resourceFacade) {
        this.operationRepository = operationRepository;
        this.farmlandFacade = farmlandFacade;
        this.resourceFacade = resourceFacade;
    }

    Either<OperationError, Operation> createOperation(UUID farmlandId, OperationType type, Money cost) {
        return farmlandFacade.getFarmlandById(farmlandId)
                             .map(farmlandDto -> {
                                 Operation operation = Operation.create(farmlandId, type, cost);
                                 operationRepository.save(operation);
                                 calculateOperationCost(operation);
                                 return operation;
                             })
                             .toEither(OperationError.INVALID_FARMLAND);
    }

    Option<Operation> getOperationById(UUID operationId) {
        return operationRepository.findById(operationId);
    }

    List<Operation> getOperationsByFarmlandId(UUID farmlandId) {
        return operationRepository.findByFarmlandId(farmlandId);
    }

    private void calculateOperationCost(Operation operation) {
        Float area = farmlandFacade.getFarmlandById(operation.getFarmlandId())
                                   .map(FarmlandDto::getArea)
                                   .get();
        operation.calculateCost(area);
    }

    Either<OperationError, Operation> createResourceOperation(UUID farmlandId, OperationType type, Money unitCost, List<NewOperationResourceDto> resources) {
        List<OperationResource> operationResources;
        operationResources = resources.stream()
                                      .map(resourceDto -> resourceFacade.findResourceById(resourceDto.getResourceId())
                                                                        .map(resource -> {
                                                                            if (resourceDto.getVariantId() != null) {
                                                                                Option<ResourceVariantDto> resourceVariant = resourceFacade
                                                                                    .findResourceVariantById(resourceDto.getVariantId());
                                                                                if (resourceVariant.isDefined()) {
                                                                                    return OperationResource
                                                                                        .create(resource.getId(), resourceVariant.get().getId(), resourceDto);
                                                                                }
                                                                            }
                                                                            return OperationResource.createWithoutVariant(resource.getId(), resourceDto);
                                                                        })
                                                                        .getOrElse(() -> null))
                                      .filter(Objects::nonNull)
                                      .collect(Collectors.toList());
        if (operationResources.size() != resources.size()) {
            return Either.left(OperationError.INVALID_DATA);
        }

        return farmlandFacade.getFarmlandById(farmlandId)
                             .map(farmlandDto -> {
                                 Operation operation = Operation.createWithResource(farmlandId, type, unitCost, operationResources);
                                 operationRepository.save(operation);
                                 calculateOperationCost(operation);
                                 return operation;
                             })
                             .toEither(OperationError.INVALID_FARMLAND);
    }

}
