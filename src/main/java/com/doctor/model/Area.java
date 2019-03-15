package com.doctor.model;

import java.util.List;

public class Area {
    private Integer areaId;
    private String name;
    private Integer level;
    private Integer refId;
    private List<Area> sons;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public List<Area> getSons() {
        return sons;
    }

    public void setSons(List<Area> sons) {
        this.sons = sons;
    }
}
