package com.gxm.dts.model.domain;

public class Demand extends Base{
    private int demand_id;             //需求id
    private int project_id;            //所属项目的id
    private String demand_name;        //需求标题
    private String demand_description; //需求描述
    private String designated_person;  //指派人
    private String demand_state;       //需求状态
    private String priority;           //优先级
    private String progress;           //进度
    private String start_date;         //计划开始日期
    private String finish_date;        //计划完成日期
    private String creation_time;      //创建时间
    private String demand_record;      //记录修改内容
    private String update_time;        //记录修改时间
    private String demand_creator;     //创建人
    private String demand_document;    //上传的文件

    public String demandDiff(Demand demand) {
        String diff = compareResult("需求标题:", this.demand_name, demand.getDemand_name())
                + compareResult("描述:", this.demand_description, demand.getDemand_description())
                + compareResult("指派人:", this.designated_person, demand.getDesignated_person())
                + compareResult("状态:", this.demand_state, demand.getDemand_state())
                + compareResult( "优先级:", this.priority, demand.getPriority())
                + compareResult("进度:", this.progress, demand.getProgress())
                + compareResult("计划开始日期:", this.start_date, demand.getStart_date())
                + compareResult("计划完成日期:", this.finish_date, demand.getFinish_date())
                ;
        return diff;
    }

    @Override
    public String toString() {
        return "demand{" +
                "demand_id=" + demand_id +
                ", project_id=" + project_id +
                ", demand_name='" + demand_name + '\'' +
                ", demand_description='" + demand_description + '\'' +
                ", designated_person='" + designated_person + '\'' +
                ", demand_state='" + demand_state + '\'' +
                ", priority='" + priority + '\'' +
                ", progress='" + progress + '\'' +
                ", start_date='" + start_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                ", creation_time='" + creation_time + '\'' +
                ", demand_record='" + demand_record + '\'' +
                ", update_time='" + update_time + '\'' +
                ", demand_creator='" + demand_creator + '\'' +
                ", demand_document='" + demand_document + '\'' +
                '}';
    }

    public int getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(int demand_id) {
        this.demand_id = demand_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getDemand_name() {
        return demand_name;
    }

    public void setDemand_name(String demand_name) {
        this.demand_name = demand_name;
    }

    public String getDemand_description() {
        return demand_description;
    }

    public void setDemand_description(String demand_description) {
        this.demand_description = demand_description;
    }

    public String getDesignated_person() {
        return designated_person;
    }

    public void setDesignated_person(String designated_person) {
        this.designated_person = designated_person;
    }

    public String getDemand_state() {
        return demand_state;
    }

    public void setDemand_state(String demand_state) {
        this.demand_state = demand_state;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
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

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public String getDemand_record() {
        return demand_record;
    }

    public void setDemand_record(String demand_record) {
        this.demand_record = demand_record;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getDemand_creator() {
        return demand_creator;
    }

    public void setDemand_creator(String demand_creator) {
        this.demand_creator = demand_creator;
    }

    public String getDemand_document() {
        return demand_document;
    }

    public void setDemand_document(String demand_document) {
        this.demand_document = demand_document;
    }

}
