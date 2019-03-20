package com.ashgeek.exploreandroid;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConnectInterface {

    @GET("posts")
    Call<List<Posts>> getPosts1();
    @GET("posts")
    Call<JsonObject> getPosts();


}
