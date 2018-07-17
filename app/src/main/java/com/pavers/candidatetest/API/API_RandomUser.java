package com.pavers.candidatetest.API;

import com.google.gson.JsonElement;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface API_RandomUser {

    @GET("api/?results")
    Observable<JsonElement> getImage(
            @Query("inc") String gender,
            @Query("gender") String sex
    );
}