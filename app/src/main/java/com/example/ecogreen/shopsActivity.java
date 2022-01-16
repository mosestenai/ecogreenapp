package com.example.ecogreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecogreen.Anime;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


public class shopsActivity extends AppCompatActivity {
    private List<Anime> lstAnime;
    private RecyclerView recyclerView;
    Button search,view;
    EditText name;
    public String username;

    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        name = findViewById(R.id.name);
        String searchv = name.getText().toString();


        url = "https://ecogreenapp.herokuapp.com/apis/get_shops_api.php?5$fgk=hh&name="+ searchv;

        search = findViewById(R.id.search);

        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();




        search.setOnClickListener(v -> {

            lstAnime = new ArrayList<>();
            recyclerView = findViewById(R.id.recyclerviewid);
            jsonrequest();
        });



    }

    private void jsonrequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Shops...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String JSON_URL = url;

        try {



            Response.Listener<JSONArray> successListener = response -> {


                for (int i = 0; i < response.length(); i++) {

                    try {
                        Anime anime = Anime.getShops(response.getJSONObject(i));
                        anime.setShop(anime.getShop());
                        anime.setPhone(anime.getPhone());
                        anime.setLocation(anime.getLocation());
                        anime.setDescription(anime.getDescription());
                        if(anime.getUrl().equals("null")){
                            anime.setUrl("https://ecogreenapp.herokuapp.com/images/pic4.jpg");
                        }else{
                            anime.setUrl(anime.getUrl());
                        }
                        progressDialog.dismiss();
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        Toast.makeText(shopsActivity.this,"No shop found with the name provided",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();


                    }

                }
                setuprecycleview(lstAnime);

            };
            Response.ErrorListener errorListener = error -> {
                if (error instanceof NoConnectionError) {
                    setContentView(R.layout.error);
                    Toast.makeText(this, "No internet access", Toast.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.error);
                    Toast.makeText(this, "No response,Make sure you have a strong internet connection", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            };
            JsonArrayRequest request = new JsonArrayRequest(JSON_URL, successListener, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);


        } catch (Exception e) {
            Toast.makeText(this, "json exception", Toast.LENGTH_LONG).show();
        }

    }

    private void setuprecycleview(List<Anime> lstAnime) {
        recycleshops myadapter = new recycleshops(this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
