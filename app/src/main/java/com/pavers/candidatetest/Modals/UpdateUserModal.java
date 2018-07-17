package com.pavers.candidatetest.Modals;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UpdateUserModal implements Serializable{

    public Integer userPermissionLevel;
    public String userTeam;
    public Integer payGrade;
    public String picture;

    public UpdateUserModal() {
    }

    public UpdateUserModal(Integer userPermissionLevel, String userTeam, Integer payGrade, String picture) {
        this.userPermissionLevel = userPermissionLevel;
        this.userTeam = userTeam;
        this.payGrade = payGrade;
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

    public Integer getpayGrade() {
        return payGrade;
    }

    public void setpayGrade(Integer PayGrade) {
        this.payGrade = PayGrade;
    }
}
