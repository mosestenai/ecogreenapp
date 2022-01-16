package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ecogreen.api.AbstractAPIListener;

public class ownerviewshop extends AppCompatActivity {

    TextView shop,paybill,description,phone,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerviewshop);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching items...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Intent test = getIntent();
        String username = test.getStringExtra("username");

        shop = findViewById(R.id.shop);
        paybill = findViewById(R.id.paybill);
        description = findViewById(R.id.description);
        phone = findViewById(R.id.phone);
        location = findViewById(R.id.location);

        String user = "tumaini";




        final Model model = Model.getInstance(ownerviewshop.this.getApplication());
        model.getshop(user, new AbstractAPIListener() {
            @Override
            public void onGetshop(User user) {
                model.setUser(user);
                shop.setText(user.getShop());
                paybill.setText(user.getPaybill());
                description.setText(user.getDescription());
                phone.setText(user.getPhone());
                location.setText(user.getLocation());

                progressDialog.dismiss();


            }


        });
    }
}