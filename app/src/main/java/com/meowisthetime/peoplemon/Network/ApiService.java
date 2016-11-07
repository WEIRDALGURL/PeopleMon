package com.meowisthetime.peoplemon.Network;

import com.meowisthetime.peoplemon.Models.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by sheamaynard on 10/31/16.
 */

public interface ApiService {

//    @POST("token")
//    Call<Account> login (@Body Account account);
//
//    @POST("register")
//    Call<Account> register (@Body Account account);

//
//
//
//    @POST("api/category/createCategory")
//    Call<Void> addCategory (@Body Category category);
//
//    @GET("api/category/getCategory/year/{year}/month/{month}/day/{day}")
//    Call<Category[]> getWeekCategories (@Path("year") Integer year,
//                                        @Path("month") Integer month,
//                                        @Path("day")Integer day);
//
//
//    @GET("api/category/getCategory/year/{year}/month/{month}")
//    Call<Category[]> getMonthCategories (@Path("year") Integer year,
//                                         @Path("month") Integer month);
//
//
//
//
//
//    @POST("api/expense/createExpense")
//    Call<Expense> addExpense (@Body Expense expense);
//
//    @GET("api/expense/getExpenses/categoryId/{categoryId}")
//    Call<Expense[]> getRecentExpenses (@Path("categoryId") Integer categoryId );
//
//    @GET("api/expense/getExpenses/year/{year}/month/{month}")
//    Call<Expense[]> getMonthExpenses (@Path("year") Integer year,
//                                      @Path("month") Integer month);


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
//    @POST("api/Account/Register")
//    Call<Account> d (@Body);
////
//
//
//
//
//
//
//
//    @GET("v1/User/Nearby")
//    Call<User> a (@Path());
//
//
//    @POST("v1/User/CheckIn")
//    Call<User> a (@Body);
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

    @POST("api/Account/Register")
    Call<Void> register (@Body Account account);


}
