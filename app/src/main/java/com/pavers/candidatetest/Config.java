package com.pavers.candidatetest;

import com.pavers.candidatetest.API.API_Database;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {

    public Retrofit databaseServer() {

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://40.118.43.110:3000/api/v1/")
                .build();
    }

    public Retrofit updateServer(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://40.118.43.110:3000/api/v1/")
                .build();
    }

    public Retrofit imageServer() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://randomuser.me/api")
                .build();
    }
}
