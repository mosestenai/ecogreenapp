package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecogreen.api.AbstractAPIListener;

public class buyitem extends AppCompatActivity {
    Button submit;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyitem);
        phone = findViewById(R.id.phone);
        submit = findViewById(R.id.submit);

        Intent test = getIntent();
        String username = test.getStringExtra("username");
        String item = test.getStringExtra("item");
       String amount = test.getStringExtra("amount");


        submit.setOnClickListener(v->{
            String phonee = phone.getText().toString();
            if(phonee.length() != 0){
                final ProgressDialog progressDialog = new ProgressDialog(buyitem.this);
                progressDialog.setMessage("Processing request ...");
                progressDialog.show();
                final Model model = Model.getInstance(buyitem.this.getApplication());
                model.buyitem(username,phonee, amount,item, new AbstractAPIListener() {
                    @Override
                    public void onBuyitem(User user) {
                        model.setUser(user);
                        if ("Success".equals(user.getError())) {
                            Toast.makeText(buyitem.this, "Complete payment by entering your pin on the STK push", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(buyitem.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });
            }else {
                Toast.makeText(buyitem.this,"Enter phone number",Toast.LENGTH_SHORT).show();
            }

        });

    }
}