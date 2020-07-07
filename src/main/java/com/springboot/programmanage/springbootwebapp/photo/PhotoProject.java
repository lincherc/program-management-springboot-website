package com.springboot.programmanage.springbootwebapp.photo;

public class PhotoProject {
    private int id;
    private int imageId;
    private int projectId;


    public PhotoProject(int imageId, int projectId) {
        this.imageId = imageId;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
