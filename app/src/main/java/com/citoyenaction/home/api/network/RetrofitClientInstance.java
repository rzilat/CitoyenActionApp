package com.citoyenaction.home.api.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;


import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
        private static final String BASE_URL = "https://citoyenaction.herokuapp.com/";

        //create logger
        private static HttpLoggingInterceptor logger=
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        //create okHttp client
        private static OkHttpClient.Builder okHttp= new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .addInterceptor(logger)
                .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder builder= original.newBuilder()
                                        .header("accept","*/*");
                                Request request=builder.build();
                                return chain.proceed(request);
                        }
                });

        public static Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setLenient()
                .create();

        public static Retrofit.Builder builder= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp.build());

        private static Retrofit retrofit= builder.build();


        public static <S> S buildService(Class<S> serviceType){
            return retrofit.create(serviceType);
        }
}
