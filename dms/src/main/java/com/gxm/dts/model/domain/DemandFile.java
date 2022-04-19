package com.gxm.dts.model.domain;

public class DemandFile {
    private int id;
    private int demand_id;
    private String file_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

}
