package pro.farmmanager.farmlands;

import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
class Farmland extends BaseEntity {

    private String name;

    @Embedded
    private FarmlandIdentify identify;

    @Embedded
    private FarmlandGeometry geometry;

    private Float area;

    private UUID ownerId;

    @Enumerated
    private Season season = Season.S2018;

    private boolean archive = false;

    static Farmland create(String name, Float area, UUID ownerId) {
        return new Farmland(name, area, ownerId);
    }

    private Farmland(String name, Float area, UUID ownerId) {
        this.name = name;
        this.area = area;
        this.ownerId = ownerId;
    }

    public void attachOwner(UUID userId) {
        this.ownerId = userId;
    }

    public void changeGeometry(FarmlandGeometry geometry) {
        this.geometry = geometry;
    }

    public void archive() {
        this.archive = true;
    }

    public void restore() {
        this.archive = false;
    }

    public void moveToSeason(Season season) {
        this.season = season;
    }

    public void changeArea(Float area) {
        this.area = area;
    }

    public void rename(String name) {
        this.name = name;
    }

    public FarmlandDto toDto() {
        return new FarmlandDto();
    }

}
