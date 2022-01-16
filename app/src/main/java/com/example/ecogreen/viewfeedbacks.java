package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class viewfeedbacks extends AppCompatActivity {
    Button quiz;
    private List<Anime> lstAnime;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedbacks);



        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Feedbacks...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String JSON_URL = "https://ecogreenapp.herokuapp.com/apis/get_feedbacks_api.php?5$fgk=hh";

        try {



            Response.Listener<JSONArray> successListener = response -> {


                for (int i = 0; i < response.length(); i++) {

                    try {
                        Anime anime = Anime.getfeebacks(response.getJSONObject(i));
                        anime.setFeedback(anime.getFeedback());
                        anime.setTime(anime.getTime());
                        progressDialog.dismiss();
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        Toast.makeText(viewfeedbacks.this,"No Feedbacks at the moment",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();


                    }

                }
                setuprecycleview(lstAnime);

            };
            Response.ErrorListener errorListener = error -> {
                if (error instanceof NoConnectionError) {
                    setContentView(R.layout.error);
                    Toast.makeText(viewfeedbacks.this, "No internet access", Toast.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.error);
                    Toast.makeText(viewfeedbacks.this, "No response,Make sure you have a strong internet connection", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            };
            JsonArrayRequest request = new JsonArrayRequest(JSON_URL, successListener, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(viewfeedbacks.this);
            requestQueue.add(request);


        } catch (Exception e) {
            Toast.makeText(viewfeedbacks.this, "json exception", Toast.LENGTH_LONG).show();
        }

    }

    private void setuprecycleview(List<Anime> lstAnime) {
        recyclefeebacks myadapter = new recyclefeebacks(this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }



}