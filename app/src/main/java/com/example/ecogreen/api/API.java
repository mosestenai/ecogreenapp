package com.example.ecogreen.api;

import android.media.Image;

import java.util.List;

import retrofit2.http.GET;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface API {
    void login(String username, String password,APIListener listener);
    void register(String username,String email,String location,String password,APIListener listener);
    void additem(String heading, String description, String price, String username,String urll,APIListener listener);
    void addshop(String name, String description, String location, String username,String password,
                 String phone,String paybill,String urll,APIListener listener);
    void addidea(String heading,String description,String link, String phone,String urll,APIListener listener);
    void addcentre(String centre,String description,String location, String phone,String type,APIListener listener);
    void resetpassword(String email,APIListener listener);
    void buyitem(String username,String phone,String amount,String item,APIListener listener);
    void postfeedback(String feedback,APIListener listener);
    void getshop(String username,APIListener listener);
    void postreply(String answer,String id,APIListener listener);
    void postquiz(String question,APIListener listener);



    @Multipart                                                                                          // POST request to upload an image from storage
    @POST("https://eazistey.co.ke/post_image_api.php?5$fgk=hh")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);

}

