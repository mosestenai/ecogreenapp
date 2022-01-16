package com.example.ecogreen.api;

import  android.app.Application;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.util.ArrayMap;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecogreen.User;
import com.example.ecogreen.loginActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class WebApi implements API {

   public static final String BASE_URL = "https://ecogreenapp.herokuapp.com/";

   private final Application mApplication;
   private final RequestQueue mRequestQueue;


   public WebApi(Application application){
       mApplication = application;
       mRequestQueue = Volley.newRequestQueue(application);
    }


   //login any user
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void login(String username, String password, final APIListener listener){
       String url = BASE_URL + "apis/login_api.php?5$fgk=hh";
       JSONObject jsonObject = new JSONObject();


       try {
          jsonObject.put("Context-Type","application/json");
          jsonObject.put("username",username);
          jsonObject.put("password",password);

          Response.Listener<JSONObject> successListener = response -> {


             try {
                User user = User.getUser(response);
                listener.onLogin(user);
                }
             catch (JSONException e) {
                try {
                   User error =User.getErr(response);
                   listener.onLogin(error);
                  // Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();

                } catch (JSONException jsonException) {
                   Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(mApplication, loginActivity.class);
                   mApplication.startActivity(intent);
                }
             }
          };


          Response.ErrorListener errorListener = error ->{
             if (error instanceof com.android.volley.NoConnectionError) {
                Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mApplication, loginActivity.class);
                mApplication.startActivity(intent);
             }else{
                Toast.makeText(mApplication, "There was an error", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mApplication, loginActivity.class);
                mApplication.startActivity(intent);
             }
          };


          JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
          mRequestQueue.add(request);
       } catch (JSONException e) {
          Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
       }

    }
    //Register a user

    public void register(String username, String email, String location, String password, final APIListener listener){
       String url = BASE_URL + "apis/register_api.php?5$fgk=hh";
       JSONObject jsonObject = new JSONObject();



       try {

          jsonObject.put("username",username);
          jsonObject.put("password",password);
          jsonObject.put("email",email);
          jsonObject.put("location",location);

          Response.Listener<JSONObject> successListener = response -> {
             try {
                User user = User.getUser(response);
                listener.onRegister(user);
             }
             catch (JSONException e) {
                try {
                  User error = User.getErr(response);
                  Toast.makeText(mApplication, "ERROR: "+ error.getError(), Toast.LENGTH_LONG).show();
                } catch (JSONException jsonException) {
                   Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();
                }

             }

          };


          Response.ErrorListener errorListener = error -> {
             //Log.v("Response",error.toString());
             if (error instanceof com.android.volley.NoConnectionError) {
                Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
             }

          };

          JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
          mRequestQueue.add(request);
       } catch (JSONException e) {
          Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
       }

    }



   //Add an item
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void additem(String heading, String description, String price, String username,String urll , final APIListener listener){
      String url = BASE_URL + "apis/post_item_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("item",heading);
         jsonObject.put("description",description);
         jsonObject.put("price",price);
         jsonObject.put("username",username);
         jsonObject.put("url",urll);


         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onAdditem(user);
            }
            catch (JSONException e) {
                  Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }

   //Add an shop
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void addshop(String name, String description, String location, String username,String password,
                       String phone,String paybill,String urll, final APIListener listener){
      String url = BASE_URL + "apis/add_shop_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("name",name);
         jsonObject.put("description",description);
         jsonObject.put("location",location);
         jsonObject.put("username",username);
         jsonObject.put("phone",phone);
         jsonObject.put("paybill",paybill);
         jsonObject.put("password",password);
         jsonObject.put("url",urll);


         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onAddshop(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }

   //Add an idea
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void addidea(String heading,String description,String link,String phone,String urll, final APIListener listener){
      String url = BASE_URL + "apis/add_idea_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("heading",heading);
         jsonObject.put("description",description);
         jsonObject.put("phone",phone);
         jsonObject.put("link",link);
         jsonObject.put("url",urll);


         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onAddidea(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //Add a collection centre
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void addcentre(String centre,String description,String location, String phone,String type, final APIListener listener){
      String url = BASE_URL + "apis/add_centre_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("centre",centre);
         jsonObject.put("description",description);
         jsonObject.put("contact",phone);
         jsonObject.put("location",location);
         jsonObject.put("type",type);


         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onAddcentre(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }

   //Reset password
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void resetpassword(String email, final APIListener listener){
      String url = BASE_URL + "apis/sendreset_link_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("email",email);



         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onResetpassword(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }

   //Buy item
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void buyitem(String username,String phone,String amount,String item, final APIListener listener){
      String url = BASE_URL + "apis/payitem_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("phone",phone);
         jsonObject.put("amount",amount);
         jsonObject.put("username",username);
         jsonObject.put("item",item);



         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onBuyitem(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }

   //post feedback
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void postfeedback(String feedback, final APIListener listener){
      String url = BASE_URL + "apis/post_feedback_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("feedback",feedback);



         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onPostfeedback(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //get SHOP item
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void getshop(String username, final APIListener listener){
      String url = BASE_URL + "apis/get_shopinfo_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("username",username);




         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getshop(response);
               //Toast.makeText(mApplication,user.getLocation(),Toast.LENGTH_SHORT).show();
               listener.onGetshop(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //Admin post reply
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void postreply(String answer,String id, final APIListener listener){
      String url = BASE_URL + "apis/reply_quiz_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("id",id);
         jsonObject.put("answer",answer);



         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onPostreply(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }


   //Admin post reply
   @RequiresApi(api = Build.VERSION_CODES.O)
   public void postquiz(String question, final APIListener listener){
      String url = BASE_URL + "apis/post_quiz_api.php?5$fgk=hh";
      JSONObject jsonObject = new JSONObject();



      try {

         jsonObject.put("question",question);




         Response.Listener<JSONObject> successListener = response -> {
            try {
               User user = User.getErr(response);
               listener.onPostquiz(user);
            }
            catch (JSONException e) {
               Toast.makeText(mApplication, "There was an error try again later", Toast.LENGTH_LONG).show();

            }

         };


         Response.ErrorListener errorListener = error -> {
            //Log.v("Response",error.toString());
            if (error instanceof NoConnectionError) {
               Toast.makeText(mApplication, "No internet access", Toast.LENGTH_LONG).show();
            }


         };


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonObject,successListener,errorListener);
         mRequestQueue.add(request);
      } catch (JSONException e) {
         Toast.makeText(mApplication, "json exception", Toast.LENGTH_LONG).show();
      }

   }










   @Override
   public Call<ResponseBody> uploadImage(MultipartBody.Part image) {
      return null;
   }


}
