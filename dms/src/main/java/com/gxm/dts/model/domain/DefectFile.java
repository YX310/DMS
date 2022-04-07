package com.gxm.dts.model.domain;

public class DefectFile {
    private int id;
    private int defect_id;
    private String file_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefect_id() {
        return defect_id;
    }

    public void setDefect_id(int defect_id) {
        this.defect_id = defect_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
