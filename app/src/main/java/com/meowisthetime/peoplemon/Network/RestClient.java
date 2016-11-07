package com.meowisthetime.peoplemon.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.meowisthetime.peoplemon.PeopleMonApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sheamaynard on 10/31/16.
 */

public class RestClient {
    private ApiService apiService;

    public RestClient() {
        //makes date object instead of string
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        Gson gson = builder.create();

        //print to log when you make a request
        //intercepting info on the way out and the way in
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);


        //worlds smallest web browser
        //sending info and receiving info
        //if it takes longer than 10 seconds time out
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new SessionRequestInterceptor())
                .addInterceptor(log)
                .build();

        //every url starts with base url
        //tying all of the things together
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(PeopleMonApplication.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //all of our gets and posts updates will be difined here
        apiService = restAdapter.create(ApiService.class);
    }


    public ApiService getApiService() {
        return apiService;
    }
}
