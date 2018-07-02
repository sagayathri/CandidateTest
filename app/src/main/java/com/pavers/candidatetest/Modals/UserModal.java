package com.pavers.candidatetest.Modals;

public class UserModal {

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
