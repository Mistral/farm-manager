package pro.farmmanager.farmlands;

import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.shared_kernel.BaseEntity;

import javax.persistence.*;

@Entity
class Farmland extends BaseEntity {

    private String name;

    @Embedded
    private FarmlandIdentify identify;

    @Embedded
    private FarmlandGeometry geometry;

    @Embedded
    private Area area;

    private Long userId;

    @Enumerated
    private Season season;

    private boolean archive;

    public void attachOwner(Long userId) {
        this.userId = userId;
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

    public void changeArea(Area area) {
        this.area = area;
    }

    public void rename(String name) {
        this.name = name;
    }

    public FarmlandDto toDto() {
        return new FarmlandDto();
    }

}
