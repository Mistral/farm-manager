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

    private Float area;

    private Long userId;

    @Enumerated
    private Season season = Season.S2018;

    private boolean archive;

    static public Farmland create(String name, Float area, Long userId) {
        return new Farmland(name, area, userId);
    }

    private Farmland(String name, Float area, Long userId) {
        this.name = name;
        this.area = area;
        this.userId = userId;
    }

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
