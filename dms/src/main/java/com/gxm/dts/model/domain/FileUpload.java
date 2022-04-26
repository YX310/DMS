package com.gxm.dts.model.domain;

public class FileUpload {
    private int id;
    private int assoc_id;
    private boolean is_defect;
    private String file_path;
    private String file_name;

    public boolean isIs_defect() {
        return is_defect;
    }

    public void setIs_defect(boolean is_defect) {
        this.is_defect = is_defect;
    }

    public int getAssoc_id() {
        return assoc_id;
    }

    public void setAssoc_id(int assoc_id) {
        this.assoc_id = assoc_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() { return file_name; }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
