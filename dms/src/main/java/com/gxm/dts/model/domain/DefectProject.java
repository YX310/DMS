package com.gxm.dts.model.domain;

public class DefectProject extends Defect {
    private String project_name;
    private String creator;

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}