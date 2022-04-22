package com.gxm.dts.model.domain;

import java.io.Serializable;

public class SearchContent implements Serializable {
    private Integer id;
    private String title;
    private String type;

    public SearchContent(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public SearchContent(Integer id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
