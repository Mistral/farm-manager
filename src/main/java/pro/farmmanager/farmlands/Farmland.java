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

    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID ownerId;

    @Enumerated
    private Season season = Season.S2018;

    private boolean archived = false;

    static Farmland create(String name, Float area, UUID ownerId) {
        return new Farmland(name, area, ownerId);
    }

    private Farmland() {

    }

    private Farmland(String name, Float area, UUID ownerId) {
        this.name = name;
        this.area = area;
        this.ownerId = ownerId;
    }

    UUID getOwnerId() {
        return ownerId;
    }

    void attachOwner(UUID userId) {
        this.ownerId = userId;
    }

    void changeGeometry(FarmlandGeometry geometry) {
        this.geometry = geometry;
    }

    void archive() {
        this.archived = true;
    }

    void restore() {
        this.archived = false;
    }

    void moveToSeason(Season season) {
        this.season = season;
    }

    void changeArea(Float area) {
        this.area = area;
    }

    void rename(String name) {
        this.name = name;
    }

    FarmlandDto toDto() {
        return new FarmlandDto(name, area, ownerId, archived);
    }

}
