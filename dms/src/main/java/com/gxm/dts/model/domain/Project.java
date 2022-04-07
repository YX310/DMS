package com.gxm.dts.model.domain;

public class Project {
    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", creator='" + creator + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", project_description='" + project_description + '\'' +
                ", project_member='" + project_member + '\'' +
                '}';
    }

    private int project_id;
    private String project_name; //项目名称
    private String creator; //创建者
    private String creation_date;  //创建时间
    private String project_description; //项目描述
    private String project_member;  //项目成员


    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
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

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProject_member() {
        return project_member;
    }

    public void setProject_member(String project_member) {
        this.project_member = project_member;
    }

}
