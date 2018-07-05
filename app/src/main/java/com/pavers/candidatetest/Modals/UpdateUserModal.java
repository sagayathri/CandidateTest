package com.pavers.candidatetest.Modals;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UpdateUserModal implements Serializable{

    @Expose private Integer userPermissionLevel;
    @Expose private String userTeam;
    @Expose private String userPayGrade;
    @Expose private String picture;

    public UpdateUserModal() {
    }

    public UpdateUserModal(Integer userPermissionLevel, String userTeam, String userPayGrade, String picture) {
        this.userPermissionLevel = userPermissionLevel;
        this.userTeam = userTeam;
        this.userPayGrade = userPayGrade;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getUserPermissionLevel() {
        return userPermissionLevel;
    }

    public void setUserPermissionLevel(Integer userPermissionLevel) {
        this.userPermissionLevel = userPermissionLevel;
    }

    public String getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    public String getUserPayGrade() {
        return userPayGrade;
    }

    public void setUserPayGrade(String userPayGrade) {
        this.userPayGrade = userPayGrade;
    }
}
