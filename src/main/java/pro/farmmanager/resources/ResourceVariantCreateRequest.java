package pro.farmmanager.resources;

public class ResourceVariantCreateRequest {

    private String name;

    private String description;

    public ResourceVariantCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
