package com.shapeworks.mivule.Entities;

import java.util.Date;

/**
 * Created by koomabenjamin on 12/11/17.
 */

public class Tasks {
    private Date startDate;
    private int duration;
    private String projectId;
    private String projectName;
    private String agentId;

    public Tasks() {
    }

    public Tasks(Date startDate, int duration, String projectId, String projectName, String agentId) {
        this.startDate = startDate;
        this.duration = duration;
        this.projectId = projectId;
        this.projectName = projectName;
        this.agentId = agentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
