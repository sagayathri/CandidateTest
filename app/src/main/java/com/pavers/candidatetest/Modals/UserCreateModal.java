package com.pavers.candidatetest.Modals;

import android.widget.AbsListView;

import java.io.Serializable;

public class UserCreateModal implements Serializable{

    private int userID;
    private String userName;
    private int userPermissionLevel;
    private String userTeam;
    private String userSex;
    private int userStartDate;
    private int isActive;
    private int userLeaveDate;
    private int payGrade;
    private String picture;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPermissionLevel() {
        return userPermissionLevel;
    }

    public void setUserPermissionLevel(int userPermissionLevel) {
        this.userPermissionLevel = userPermissionLevel;
    }

    public String getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getUserStartDate() {
        return userStartDate;
    }

    public void setUserStartDate(int userStartDate) {
        this.userStartDate = userStartDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getUserLeaveDate() {
        return userLeaveDate;
    }

    public void setUserLeaveDate(int userLeaveDate) {
        this.userLeaveDate = userLeaveDate;
    }

    public int getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(int payGrade) {
        this.payGrade = payGrade;
    }
}
