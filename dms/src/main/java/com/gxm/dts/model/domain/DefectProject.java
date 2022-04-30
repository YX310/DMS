package com.gxm.dts.model.domain;

public class DefectProject extends Defect {
    private String project_name;
    private int project_id;
    private String creator;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public int getProject_id() {
        return project_id;
    }

    @Override
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}