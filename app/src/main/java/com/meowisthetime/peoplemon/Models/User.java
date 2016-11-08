package com.meowisthetime.peoplemon.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by sheamaynard on 11/7/16.
 */

public class User {

    @SerializedName("UserId")
    private String userid;

    @SerializedName("UserName")
    private String userName;

    @SerializedName("avatarBase64")
    private String avatarBase64;

    @SerializedName("Longitude")
    private Double longitude;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Created")
    private Date created;

    @SerializedName("CaughtUserId")
    private String caughtUserId;

    @SerializedName("RadiusInMeters")
    private Double radiusInMeters;


    public User() {
    }


    public User(String userid, String userName, String avatarBase64, Double longitude, Double latitude, Date created) {
        this.userid = userid;
        this.userName = userName;
        this.avatarBase64 = avatarBase64;
        this.longitude = longitude;
        this.latitude = latitude;
        this.created = created;
    }

    public User(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCaughtUserId() {
        return caughtUserId;
    }

    public void setCaughtUserId(String caughtUserId) {
        this.caughtUserId = caughtUserId;
    }

    public Double getRadiusInMeters() {
        return radiusInMeters;
    }

    public void setRadiusInMeters(Double radiusInMeters) {
        this.radiusInMeters = radiusInMeters;
    }
}

