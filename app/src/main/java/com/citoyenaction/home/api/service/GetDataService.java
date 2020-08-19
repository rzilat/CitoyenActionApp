package com.citoyenaction.home.api.service;



import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.ActUpload;
import com.citoyenaction.home.api.model.User;

import java.util.List;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Headers({"Content-Type: multipart/form-data"})
    @Multipart
    @POST("citoyenaction/actupload")
     Call<ActUpload> saveActUpload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    @GET("citoyenaction/actnoncivique/{actNonCiviqueId}")
    Call<ActNonCivique> getActNonCivique(@Path("actNonCiviqueId")long actNonCiviqueId);

    @GET("citoyenaction/actnonciviques")
    Call<List<ActNonCivique>> findAll();



}
