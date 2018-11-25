package pro.farmmanager.farmlands.dto;

public class FarmlandCreateRequest {

    private final String name;

    private final Float area;

    FarmlandCreateRequest(String name, Float area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public Float getArea() {
        return area;
    }

}
