package com.pavers.candidatetest.API;

import android.widget.ImageView;

import com.pavers.candidatetest.Modals.APIResponseModal;
import com.pavers.candidatetest.Modals.UpdateUserModal;
import com.pavers.candidatetest.Modals.UserCreateModal;
import com.pavers.candidatetest.Modals.UserHeaderModal;
import com.pavers.candidatetest.Modals.UserModal;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface API_Database {

    //Users

    @GET("users")
    Observable<List<UserModal>> getAllUsers();

    @DELETE("users?")
    Observable<APIResponseModal> deleteUser(
            @Query("userID") int userID
    );

    @POST("users")
    Observable<UserCreateModal> createUser(
            @Body UserCreateModal userCreateModal
    );

    @PUT("users?")
    Observable<UpdateUserModal> updateUser(
            @Query("userID") int userID,
            @Body RequestBody body
    );
}