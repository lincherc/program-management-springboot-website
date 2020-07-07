package com.springboot.programmanage.springbootwebapp.project;

import com.springboot.programmanage.springbootwebapp.photo.Photo;
import com.springboot.programmanage.springbootwebapp.user.Account;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int projectId;
    private String projectName;
    private String projectIntroduction;
    private int isPassed;
    private String fileUrl;
    private Account personInCharge;
    private List<Photo> projectPhotos;


    public Project(){
        this.personInCharge=new Account();
        this.projectPhotos =new ArrayList<>();
    }

    public Project(String projectName, String projectIntroduction, String fileUrl, Account personInCharge, List<Photo> projectPhotos) {
        this.projectName = projectName;
        this.projectIntroduction = projectIntroduction;
        this.fileUrl = fileUrl;
        this.personInCharge = personInCharge;
        this.projectPhotos = projectPhotos;
    }



    public List<Photo> getProjectPhotos() {
        return projectPhotos;
    }

    public void setProjectPhotos(List<Photo> projectPhotos) {
        this.projectPhotos = projectPhotos;
    }

    public Account getPersonInCharge() {
        return this.personInCharge;
    }

    public void setPersonInCharge(Account personInCharge) {
        this.personInCharge = personInCharge;
    }

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
