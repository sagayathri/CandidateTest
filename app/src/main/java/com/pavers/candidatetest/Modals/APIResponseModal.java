package com.pavers.candidatetest.Modals;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class APIResponseModal {

    @SerializedName ("responseCode") private int ResponseCode;
    @SerializedName ("responseMessage") private String ResponseMessage;
    @SerializedName("sucess") private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }
}