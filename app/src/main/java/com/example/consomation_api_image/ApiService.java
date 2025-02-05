package com.example.consomation_api_image;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}
