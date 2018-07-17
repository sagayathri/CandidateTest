package com.pavers.candidatetest.Controller;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pavers.candidatetest.API.API_Database;
import com.pavers.candidatetest.API.API_RandomUser;
import com.pavers.candidatetest.Adaptors.UserAdaptor;
import com.pavers.candidatetest.Config;
import com.pavers.candidatetest.Modals.APIResponseModal;
import com.pavers.candidatetest.Modals.UpdateUserModal;
import com.pavers.candidatetest.Modals.UserCreateModal;
import com.pavers.candidatetest.Modals.UserImageModal;
import com.pavers.candidatetest.Modals.UserModal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserController {

    private API_Database api_database;
    private List<UserModal> allUsers;
    private Activity mainActivity;
    private RecyclerView rvUser;
    private API_RandomUser api_randomUser;
    private UserModal userModal;
    private UserImageModal userImageModal;

    public UserController(Activity _mainActivity, RecyclerView _rvUser) {

        this.mainActivity = _mainActivity;
        this.rvUser = _rvUser;
        userModal = new UserModal();
        userImageModal = new UserImageModal();

        Config config = new Config();
        Retrofit rfDatabase = config.databaseServer();
        api_database = rfDatabase.create(API_Database.class);

        getUsers();
        /*try {
            createUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public UserController(Activity _mainActivity) {
        this.mainActivity = _mainActivity;
    }

    private void getUsers() {

        Observable<List<UserModal>> getAllUsers = api_database.getAllUsers();
        getAllUsers
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserModal>>() {
                    @Override
                    public void onCompleted() {
                        displayUsers();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Fetch All Users", e.getMessage());
                    }

                    @Override
                    public void onNext(List<UserModal> userModals) {
                        if(userModals.size() > 0) {
                            for (int i = 0; i < userModals.size(); i++) {
                                allUsers = userModals;
                                UserModal modal = userModals.get(i);
                                int temp = modal.getUserInfoModal().getUserLeaveDate();
                                String tempDate = String.valueOf(modal.getUserInfoModal().getUserLeaveDate());
                                if (temp != 0 || tempDate != null) {
                                    allUsers.remove(i);
                                    deleteUser(modal.getUserHeaderModal().getUserID());
                                }
                            }
                        }
                    }
                });
    }

    private class sortedUser implements Comparator <UserModal>{
        @Override
        public int compare(UserModal o1, UserModal o2) {
            return o1.getUserHeaderModal().getUserName().compareToIgnoreCase(o2.getUserHeaderModal().getUserName());
        }
    }

    private void displayUsers() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        Collections.sort(allUsers, new sortedUser());
        UserAdaptor userAdaptor = new UserAdaptor(allUsers, mainActivity);
        rvUser.setLayoutManager(linearLayoutManager);
        rvUser.setAdapter(userAdaptor);
    }

    public void deleteUser(int userID) {
        Config config = new Config();
        Retrofit rfDatabase = config.databaseServer();
        api_database = rfDatabase.create(API_Database.class);
        Observable<APIResponseModal> deleteuser = api_database.deleteUser(userID);
        deleteuser.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<APIResponseModal>() {
                    @Override
                    public void onNext(APIResponseModal apiResponseModal) {
                        apiResponseModal.getResponseCode();
                        apiResponseModal.getResponseMessage();
                    }
                    @Override
                    public void onCompleted() {
                        Toast.makeText(mainActivity, "User Deleted", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Throwable e) {
                    }

                });
    }

    public void setupdatedUser(final int userID, RequestBody body) {
        Config config = new Config();
        Retrofit rfDatabase = config.databaseServer();
        api_database = rfDatabase.create(API_Database.class);

        final Observable<UpdateUserModal> updateUser = api_database.updateUser(userID, body);
        updateUser.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateUserModal>() {
                    @Override
                    public void onCompleted() {
                        getUsers();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("error", String.valueOf(e));
                    }

                    @Override
                    public void onNext(UpdateUserModal updateUserModal) {

                    }
                });
    }

    public void createUser() throws InterruptedException {

        final UserCreateModal userCreateModal = new UserCreateModal();
        userCreateModal.setUserName("Test User");
        userCreateModal.setUserPermissionLevel(0);
        userCreateModal.setUserSex("M");
        userCreateModal.setUserTeam("Picking");
        userCreateModal.setPayGrade(0);
        userCreateModal.setIsActive(1);
        userCreateModal.setUserStartDate(1488266592);
        String gender = userCreateModal.getUserSex();
        getpicture(userCreateModal.getUserID(),gender);
        Thread.sleep(5000);
        userCreateModal.setPicture(userImageModal.getPicture());
        Config config = new Config();
        Retrofit rfDatabase = config.databaseServer();
        api_database = rfDatabase.create(API_Database.class);

        Observable<UserCreateModal> createUsercall = api_database.createUser(userCreateModal);

        createUsercall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserCreateModal>() {
                    @Override
                    public void onCompleted() {
                        getUsers();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserCreateModal userCreateModal) {
                        UserCreateModal userCreateModal1 = userCreateModal;
                    }
                });
    }

    private void getpicture(final int userID, String gender) {
        if (gender == "M")
            gender = "male";
        else if (gender == "F")
            gender = "female";

        Config config = new Config();
        Retrofit rfDatabase = config.imageServer();
        api_randomUser = rfDatabase.create(API_RandomUser.class);

        Observable<JsonElement> getimage = api_randomUser.getImage("gender,picture",gender);
        final JsonElement[] picture = new JsonElement[1];
        final String[] picturestr = new String[1];
        getimage.subscribeOn(Schedulers.newThread())
                // .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonElement>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Error", String.valueOf(e));
                    }

                    @Override
                    public void onNext(JsonElement jsonElement) {
                        JsonObject jsonObject = (JsonObject) jsonElement;
                        JsonElement json = jsonObject.getAsJsonArray("results").get(0);
                        JsonObject pic = (JsonObject) json.getAsJsonObject().get("picture");
                        picture[0] = pic.get("thumbnail");
                        picturestr[0] = String.valueOf(picture[0]);
                        userImageModal.setPicture(picturestr[0]);
                        userImageModal.setUserID(userID);
                    }
                });
    }
}