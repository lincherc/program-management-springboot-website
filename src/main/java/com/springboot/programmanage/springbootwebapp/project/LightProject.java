package com.springboot.programmanage.springbootwebapp.project;

public class LightProject {

    private int projectId;
    private String projectName;
    private String projectIntroduction;
    private int isPassed;
    private String fileUrl;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIntroduction() {
        return projectIntroduction;
    }

    public void setProjectIntroduction(String projectIntroduction) {
        this.projectIntroduction = projectIntroduction;
    }

    public int getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(int isPassed) {
        this.isPassed = isPassed;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
