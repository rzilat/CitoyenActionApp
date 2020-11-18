package com.citoyenaction.home.api.service;



import com.citoyenaction.home.api.model.ActNonCivique;
import com.citoyenaction.home.api.model.ActUpload;
import com.citoyenaction.home.api.model.Reaction;
import com.citoyenaction.home.api.model.User;

import java.io.File;
import java.util.List;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("citoyenaction/loginuser/{email}/{password}")
    Call<User> getUserByEmailAndPassword(@Path(value="email") String email,@Path(value="password") String password);

    @POST("citoyenaction/user")
    Call<User> addUser(@Body User user);

    @GET("citoyenaction/user/{userId}")
    Call<User> getUser(@Path("userId")long userId);

    @PUT("citoyenaction/user")
    Call<User> updateUser(@Body User user);

    @POST("citoyenaction/actnoncivique")
    Call<ActNonCivique> addActNonCivique(@Body ActNonCivique actNonCivique);

    @GET("citoyenaction/actnonciviques/findbyuserid/{userId}")
    Call<List<ActNonCivique>> getActNonCiviqueByUserId(@Path(value = "userId") long userId);



    @Multipart
    @POST("citoyenaction/actupload")
    Call<String> saveActUpload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    @GET("citoyenaction/actnoncivique/{actNonCiviqueId}")
    Call<ActNonCivique> getActNonCivique(@Path("actNonCiviqueId")long actNonCiviqueId);

    @GET("citoyenaction/actnonciviques")
    Call<List<ActNonCivique>> findAll();

    @PUT("citoyenaction/actnoncivique")
    Call<ActNonCivique> updateActNonCivique(@Body ActNonCivique actNonCivique);

    @POST("citoyenaction/reaction")
    Call<Reaction> addReaction(@Body Reaction reaction);

    @GET("citoyenaction/reactions/findbyactnonciviqueId/{actNonCiviqueId}")
    Call<List<Reaction>> getReactionsByActNonCiviqueId(@Path(value = "actNonCiviqueId") long actNonCiviqueId);



}
