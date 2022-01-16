package com.example.ecogreen;
import  android.app.Application;

import com.example.ecogreen.api.API;
import com.example.ecogreen.api.APIListener;
import com.example.ecogreen.api.AbstractAPIListener;
import com.example.ecogreen.api.WebApi;
import com.example.ecogreen.api.AbstractAPIListener;

public class Model {
    //to make sure there is only one instance of a model class
    private static  Model sInstance = null;

    private final API mApi;
    private User mUser;

    public static Model getInstance(Application application) {

        if (sInstance == null) {
            sInstance = new Model(application);
        }return  sInstance;
    }

    private final Application mApplication;
    private Model(Application application) {
        mApplication = application;
        mApi = new WebApi(mApplication);
    }
    public Application getApplication() { return mApplication;}


    public void login(String username, String password, AbstractAPIListener listener){
        mApi.login(username, password, listener);
    }
    public  void register(String username,String email, String location,String password,APIListener listener){
        mApi.register(username,email,location,password,listener);
    }
    public void additem(String heading, String description, String price, String username, String urll, AbstractAPIListener listener){
        mApi.additem(heading,description,price,username,urll,listener);
    }
    public void addshop(String name, String description, String location, String username,String password,
                        String phone,String paybill,String urll, AbstractAPIListener listener){
        mApi.addshop(name,description,location,username,password,phone,paybill,urll,listener);
    }
    public void addidea(String heading, String description, String link, String phone,  String urll, AbstractAPIListener listener){
        mApi.addidea(heading,description,link,phone,urll,listener);
    }
    public void addcentre(String centre,String description,String location, String phone,String type, AbstractAPIListener listener){
        mApi.addcentre(centre,description,location,phone,type,listener);
    }
    public void resetpassword(String email,AbstractAPIListener listener){
        mApi.resetpassword(email,listener);
    }
    public void buyitem(String username,String phone,String amount,String item, AbstractAPIListener listener){
        mApi.buyitem(username,phone,amount,item,listener);
    }
    public void postfeedback(String feedback,AbstractAPIListener listener){
        mApi.postfeedback(feedback,listener);
    }
    public void getshop(String username,AbstractAPIListener listener){
        mApi.getshop(username,listener);
    }
    public void postreply(String answer,String id,AbstractAPIListener listener){
        mApi.postreply(answer,id,listener);
    }
    public void postquiz(String question,AbstractAPIListener listener){
        mApi.postquiz(question,listener);
    }




    public User getUser() {
        return mUser;
    }
   public void seterror(User error){this.mUser= error ;}
    public void setUser(User user) {
        this.mUser = user;
    }
}

