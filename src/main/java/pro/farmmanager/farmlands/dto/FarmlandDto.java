package pro.farmmanager.farmlands.dto;

import java.util.UUID;

public class FarmlandDto {

    final private String name;

    final private Float area;

    final private UUID ownerId;

    final private boolean archived;

    public FarmlandDto(String name, Float area, UUID ownerId, boolean archived) {
        this.name = name;
        this.area = area;
        this.ownerId = ownerId;
        this.archived = archived;
    }

    public String getName() {
        return name;
    }

    public Float getArea() {
        return area;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public boolean isArchived() {
        return archived;
    }

}
