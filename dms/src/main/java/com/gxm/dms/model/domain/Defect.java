package com.gxm.dms.model.domain;

public class Defect {
    private int defect_id;             //缺陷id
    private String defect_name;        //缺陷标题
    private String defect_description; //缺陷描述
    private String priority;           //优先级
    private String probability;        //出现概率
    private String project_version;    //项目版本
    private String defect_creator;     //创建人
    private String designated_person;  //指派人
    private String defect_module;      //缺陷所属模块
    private String defect_type;        //缺陷类型
    private String start_date;         //计划开始日期
    private String finish_date;        //计划完成日期
    private String progress;           //进度
    private String associated_defects; //关联已有缺陷
    private String defect_document;    //上传的文件

    public int getDefect_id() {
        return defect_id;
    }

    public void setDefect_id(int defect_id) {
        this.defect_id = defect_id;
    }

    public String getDefect_name() {
        return defect_name;
    }

    public void setDefect_name(String defect_name) {
        this.defect_name = defect_name;
    }

    public String getDefect_description() {
        return defect_description;
    }

    public void setDefect_description(String defect_description) {
        this.defect_description = defect_description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getProject_version() {
        return project_version;
    }

    public void setProject_version(String project_version) {
        this.project_version = project_version;
    }

    public String getDefect_creator() {
        return defect_creator;
    }

    public void setDefect_creator(String defect_creator) {
        this.defect_creator = defect_creator;
    }

    public String getDesignated_person() {
        return designated_person;
    }

    public void setDesignated_person(String designated_person) {
        this.designated_person = designated_person;
    }

    public String getDefect_module() {
        return defect_module;
    }

    public void setDefect_module(String defect_module) {
        this.defect_module = defect_module;
    }

    public String getDefect_type() {
        return defect_type;
    }

    public void setDefect_type(String defect_type) {
        this.defect_type = defect_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getAssociated_defects() {
        return associated_defects;
    }

    public void setAssociated_defects(String associated_defects) {
        this.associated_defects = associated_defects;
    }

    public String getDefect_document() {
        return defect_document;
    }

    public void setDefect_document(String defect_document) {
        this.defect_document = defect_document;
    }
}
