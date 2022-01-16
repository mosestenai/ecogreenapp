package com.example.ecogreen;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class testImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);
    }


    public void Upload(View v) {
        startActivity(new Intent(this, Upload.class));                     // Start the activity to upload an image
    }


}