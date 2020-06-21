package com.citoyenaction.home.api.service;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("citoyenaction/message")
    Call<String> getMessage();

    @GET("citoyenaction/loginuser/{email}/{password}")
    Call<String> getUserByEmailAndPassword(@Path(value="email") String email,@Path(value="password") String password);

}
