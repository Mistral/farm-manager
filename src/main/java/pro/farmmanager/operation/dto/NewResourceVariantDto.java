package pro.farmmanager.operation.dto;

public class NewResourceVariantDto {

    private String name;

    private String description;

    public NewResourceVariantDto(String name, String description) {
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
