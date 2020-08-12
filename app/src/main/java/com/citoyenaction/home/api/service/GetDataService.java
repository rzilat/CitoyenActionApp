package com.citoyenaction.home.api.service;



import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.User;

import java.util.List;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("citoyenaction/message")
    Call<String> getMessage();

    @GET("citoyenaction/loginuser/{email}/{password}")
    Call<User> getUserByEmailAndPassword(@Path(value="email") String email,@Path(value="password") String password);

    @POST("citoyenaction/user")
    Call<User> addUser(@Body User user);

    @POST("citoyenaction/actnoncivique")
    Call<ActNonCivique> addActNonCivique(@Body ActNonCivique actNonCivique);

    @GET("citoyenaction/actnonciviques/findbyuserid/{userId}")
    Call<List<ActNonCivique>> getActNonCiviqueByUserId(@Path(value = "userId") long userId);



}
