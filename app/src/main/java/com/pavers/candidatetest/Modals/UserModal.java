package com.pavers.candidatetest.Modals;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class UserModal implements Serializable {

    private UserHeaderModal userHeaderModal;
    private UserInfoModal userInfoModal;
    private UserImageModal userImageModal;

    public UserHeaderModal getUserHeaderModal() {
        return userHeaderModal;
    }

    public void setUserHeaderModal(UserHeaderModal userHeaderModal) {
        this.userHeaderModal = userHeaderModal;
    }

    public UserInfoModal getUserInfoModal() {
        return userInfoModal;
    }

    public void setUserInfoModal(UserInfoModal userInfoModal) {
        this.userInfoModal = userInfoModal;
    }

    public UserImageModal getUserImageModal() {
        return userImageModal;
    }

    public void setUserImageModal(UserImageModal userImageModal) {
        this.userImageModal = userImageModal;
    }
}