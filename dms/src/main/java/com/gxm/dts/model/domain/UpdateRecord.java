package com.gxm.dts.model.domain;

public class UpdateRecord {
    private int id;
    private int assoc_id;
    private long update_time;
    private String record_content;
    private String update_person;
    private boolean is_defect;
    private int project_id;
    private String assoc_title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssoc_id() {
        return assoc_id;
    }

    public void setAssoc_id(int assoc_id) {
        this.assoc_id = assoc_id;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getRecord_content() {
        return record_content;
    }

    public void setRecord_content(String record_content) {
        this.record_content = record_content;
    }

    public String getUpdate_person() {
        return update_person;
    }

    public void setUpdate_person(String update_person) {
        this.update_person = update_person;
    }

    public boolean isIs_defect() {
        return is_defect;
    }

    public void setIs_defect(boolean is_defect) {
        this.is_defect = is_defect;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getAssoc_title() {
        return assoc_title;
    }

    public void setAssoc_title(String assoc_title) {
        this.assoc_title = assoc_title;
    }
}
