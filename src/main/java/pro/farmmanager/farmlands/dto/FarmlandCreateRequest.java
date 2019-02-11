package pro.farmmanager.farmlands.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PositiveOrZero;

public class FarmlandCreateRequest {

    @Length(min = 3, max = 30)
    private final String name;

    @PositiveOrZero
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
