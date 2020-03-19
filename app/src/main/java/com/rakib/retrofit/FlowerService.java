package com.rakib.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// by convention this type of interface called "ExampleService"
public interface FlowerService {
    //call is a class, which work is whatever response came here, it'll
    //rap the data
    @GET("feeds/flowers.json")
    Call<List<FlowerResponse>> getAllFlowers();
}
