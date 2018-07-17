package com.pavers.candidatetest.Modals;

import java.io.Serializable;

public class UserImageModal implements Serializable {

    private String picture;
    private int userID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}