package com.gxm.dts.model.domain;

public class DefectProject extends Defect {
    private String project_name;
    private String project_id;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String getProject_id() {
        return project_id;
    }

    @Override
    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }
}