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

public class faqsActivity extends AppCompatActivity {
Button quiz;
    private List<Anime> lstAnime;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        quiz = findViewById(R.id.quiz);

        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();

        quiz.setOnClickListener(v->{
            Intent intent = new Intent(faqsActivity.this,postQuestion.class);
            startActivity(intent);
        });
    }

    private void jsonrequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching FAQ'S...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String JSON_URL = "https://ecogreenapp.herokuapp.com/apis/get_quizes_api.php?5$fgk=hh";

        try {



            Response.Listener<JSONArray> successListener = response -> {


                for (int i = 0; i < response.length(); i++) {

                    try {
                        Anime anime = Anime.getfaqs(response.getJSONObject(i));
                        anime.setQuestion(anime.getQuestion());
                        if(anime.getAnswer().equals("null")){
                            anime.setAnswer("To be answered");
                        }else{
                            anime.setAnswer(anime.getAnswer());
                        }

                        progressDialog.dismiss();
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        Toast.makeText(faqsActivity.this,"No FAQ'S at the moment",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();


                    }

                }
                setuprecycleview(lstAnime);

            };
            Response.ErrorListener errorListener = error -> {
                if (error instanceof NoConnectionError) {
                    setContentView(R.layout.error);
                    Toast.makeText(faqsActivity.this, "No internet access", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(faqsActivity.this, "No response", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            };

            JsonArrayRequest request = new JsonArrayRequest(JSON_URL, successListener, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(faqsActivity.this);
            requestQueue.add(request);


        } catch (Exception e) {
            Toast.makeText(faqsActivity.this, "json exception", Toast.LENGTH_LONG).show();
        }

    }

    private void setuprecycleview(List<Anime> lstAnime) {
        faqsrecycler myadapter = new faqsrecycler(this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }



}