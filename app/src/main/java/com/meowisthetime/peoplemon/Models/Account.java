package com.meowisthetime.peoplemon.Models;

import com.google.gson.annotations.SerializedName;
import com.meowisthetime.peoplemon.Components.Constants;

import java.util.Date;

/**
 * Created by sheamaynard on 11/7/16.
 */

public class Account {

    @SerializedName("Id")
    private String id;

    @SerializedName("Email")
    private String email;

    @SerializedName("HasRegistered")
    private Boolean hasRegistered;

    @SerializedName("LoginProvider")
    private String loginProvider;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("AvatarBase64")
    private String avatarBase64;

    @SerializedName("LastCheckInLongitude")
    private Double lastLong;

    @SerializedName("LastCheckInLatitude")
    private Double lastLat;

    @SerializedName("LastCheckInDateTime")
    private String lastCheckinDateTime;

    @SerializedName("password")
    private String password;

    @SerializedName("newPassword")
    private String newPassword;

    @SerializedName("oldPassword")
    private String oldPassword;

    @SerializedName("confirmPassword")
    private String confirmPassword;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("access_token")
    private String token;

    @SerializedName(".expires")
    private Date expiration;

    @SerializedName("grantType")
    private String grantType;


//    public Account(String username, String password) {
//    }

    public Account(String email, String fullName, String avatarBase64, String apiKey, String password) {
        this.email = email;
        this.fullName = fullName;
        this.avatarBase64 = avatarBase64;
        this.apiKey = Constants.apiKey;
        this.password = password;
    }

    public Account(String grantType, String email, String password) {
        this.grantType = Constants.grantType;
        this.email = email;
        this.password = password;
    }

    public Account(String fullName, String avatarBase64) {
        this.fullName = fullName;
        this.avatarBase64 = avatarBase64;
    }

    public Account(String id, String email, Boolean hasRegistered, String loginProvider, String fullName, String avatarBase64, Double lastLong, Double lastLat, String lastCheckinDateTime) {
        this.id = id;
        this.email = email;
        this.hasRegistered = hasRegistered;
        this.loginProvider = loginProvider;
        this.fullName = fullName;
        this.avatarBase64 = avatarBase64;
        this.lastLong = lastLong;
        this.lastLat = lastLat;
        this.lastCheckinDateTime = lastCheckinDateTime;
    }

    public Double getLastLong() {
        return lastLong;
    }

    public void setLastLong(Double lastLong) {
        this.lastLong = lastLong;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Double getLastLat() {
        return lastLat;
    }

    public void setLastLat(Double lastLat) {
        this.lastLat = lastLat;
    }

    public String getLastCheckinDateTime() {
        return lastCheckinDateTime;
    }

    public void setLastCheckinDateTime(String lastCheckinDateTime) {
        this.lastCheckinDateTime = lastCheckinDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasRegistered() {
        return hasRegistered;
    }

    public void setHasRegistered(Boolean hasRegistered) {
        this.hasRegistered = hasRegistered;
    }

    public String getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        this.loginProvider = loginProvider;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
