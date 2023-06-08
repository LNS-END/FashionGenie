package com.example.fashion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyAPI {
    @POST("/v1data")
    Call<Void> sendBagData(@Body List<Bag> bag);

    @POST("/v1data")
    Call<Void> sendHeadwearData(@Body List<HeadWear> headwear);

    @POST("/v1data")
    Call<Void> sendOuterData(@Body List<com.example.fashion.Outer> outer);

    @POST("/v1data")
    Call<Void> sendPantsData(@Body List<Pants> pants);

    @POST("/v1data")
    Call<Void> sendShoesData(@Body List<com.example.fashion.Shoes> shoes);

    @POST("/v1data")
    Call<Void> sendTopData(@Body List<com.example.fashion.Top> top);

//    @POST("/Coordi")
//    Call<Void> sendCoordiData(@Body Coordi coordi);

//    @POST("/LikedCoordi")
//    Call<Void> sendLikedCoordiData(@Body LikedCoordi likedCoordi);

}
