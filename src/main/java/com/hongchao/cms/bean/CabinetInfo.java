package com.hongchao.cms.bean;

/**
 * Created by godlikehzj on 2017/5/30.
 */
public class CabinetInfo {
    private Long houseId;
    private Integer doorId;
    private Integer outdoor;
    private Integer tank;
    private Integer weight;

    public CabinetInfo() {
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    public Integer getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(Integer outdoor) {
        this.outdoor = outdoor;
    }

    public Integer getTank() {
        return tank;
    }

    public void setTank(Integer tank) {
        this.tank = tank;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
