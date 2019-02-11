package pro.farmmanager.resources;

public class ResourceErrorResponse {

    private ResourceError error;

    private String message;

    public ResourceErrorResponse(ResourceError resourceError) {
        error = resourceError;

        message = resourceError.toString();
    }

    public ResourceError getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
