package com.meowisthetime.peoplemon.Network;

import com.meowisthetime.peoplemon.Models.Account;
import com.meowisthetime.peoplemon.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by sheamaynard on 10/31/16.
 */

public interface ApiService {

//    @GET("api/Account/UserInfo")
//    Call<Account[]> getUserInfo (@Path("userInfo"));
//
//
//    @POST("api/Account/UserInfo")
//    Call<Account> addUserInfo (@Body);
//
//    @POST("api/Account/Logout")
//    Call<Account> a (@Body);
//
//    @POST("api/Account/ChangePassword")
//    Call<Account> b (@Body);
//
//    @POST("api/Account/SetPassword")
//    Call<Account> c (@Body);
//

    @POST("api/Account/Register")
    Call<Void> register (@Body Account account);




    @GET("v1/User/Nearby")
    Call<User[]> findNearby(@Query("radiusInMeters") Integer radiusInMeters);


//    @POST("v1/User/CheckIn")
//    Call<Void> checkin (@Body ("Latlong") Double Latlong);
//
//    @POST("v1/User/Catch")
//    Call<User> a (@Body);
//
//
//    @GET ("v1/User/Caught")
//    Call<User> a (@Path());
//
//
//    @GET("v1/User/Conversations")
//    Call<User> a (@Path());
//
//
//    @GET("v1/User/Conversation")
//    Call<User> a (@Path());
//
//
//    @POST("v1/User/Conversation")
//    Call<User> a (@Body);
//

    @FormUrlEncoded
    @POST("token")
    Call<Account> login (@Field(value = "grant_type", encoded = true) String grantType,
                         @Field(value = "username", encoded = true) String username,
                         @Field(value = "password", encoded = true) String password);



}
