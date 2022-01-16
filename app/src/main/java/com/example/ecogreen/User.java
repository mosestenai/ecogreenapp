package com.example.ecogreen;


import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

public class User {




    public static User getUser(JSONObject jsonObject) throws JSONException{
        String token = jsonObject.getString("token");
        String username = jsonObject.getString("username");
        String permission = jsonObject.getString("permission");
        String id = jsonObject.getString("id");

        return new User(token,username,permission,id);
    }
    public static User getshop(JSONObject jsonObject) throws JSONException{
        String phone = jsonObject.getString("phone");
        String description = jsonObject.getString("description");
        String location= jsonObject.getString("location");
        String paybill = jsonObject.getString("paybill");
        String shop = jsonObject.getString("shop");

        return new User(paybill,phone,location,description,shop);
    }
    public User (String token,String username,String permission,String id){
    this.token = token;
    this.username = username;
    this.permission = permission;
    this.id = id;
    }
    private String username;
    private String permission;
    private String id;
    private String token;
    private String error;
    private String paybill;
    private String shop;
    private String phone;
    private String location;
    private String description;

    public static User getErr(JSONObject jsonObject) throws JSONException{
        String error = jsonObject.getString("error");
        String permission = "test";


        return new User(error,permission);
    }
    public User(String error, String permission) {
        this.error= error;
        this.permission = permission;

    }
    public User(String paybill, String phone, String location, String description, String shop) {
        this.paybill = paybill;
        this.phone = phone;
        this.location = location;
        this.shop = shop;
        this.description = description;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPaybill() {
        return paybill;
    }

    public void setPaybill(String paybill) {
        this.paybill = paybill;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User(String error){
    this.error= error;
    }


    public String getError() { return error; }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}



