package pro.farmmanager.farmlands.dto;

import java.util.UUID;

public class FarmlandDto {

    private final UUID id;

    private final String name;

    private final Float area;

    private final UUID ownerId;

    private final boolean archived;

    public FarmlandDto(UUID id, String name, Float area, UUID ownerId, boolean archived) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.ownerId = ownerId;
        this.archived = archived;
    }

    public UUID getId() {
        return id;
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
