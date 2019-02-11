package pro.farmmanager.resources;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.farmmanager.resources.dto.ResourceCreateRequest;

import java.util.UUID;

@RestController
@RequestMapping(value = "resources")
class ResourceController {

    private ResourceFacade resourceFacade;

    @Autowired
    ResourceController(ResourceFacade resourceFacade) {
        this.resourceFacade = resourceFacade;
    }

    @GetMapping
    public ResponseEntity getResources(@RequestParam(required = false) String type) {
        if (type != null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(resourceFacade.getResources());
        }
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity getResource(@PathVariable UUID resourceId) {
        return resourceFacade.findResourceById(resourceId)
                             .map(ResponseEntity::ok)
                             .getOrElse(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity storeResource(@RequestBody ResourceCreateRequest form) {
        Either<ResourceError, UUID> resource;
        if (form.getVariants() != null && form.getVariants().size() != 0) {
            resource = resourceFacade.createResourceWithVariant(form.getName(), form.getDescription(), form.getType(), form.getVariants());
        }
        else {
            resource = resourceFacade.createResource(form.getName(), form.getDescription(), form.getType());
        }

        if (resource.isRight()) {
            return new ResponseEntity<>(resource.map(uuid -> resourceFacade.findResourceById(uuid).get()).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(resource.mapLeft(ResourceErrorResponse::new).getLeft(), HttpStatus.BAD_REQUEST);
        }
    }

}
