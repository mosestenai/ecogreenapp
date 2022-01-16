package com.example.ecogreen;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ecogreen.api.AbstractAPIListener;

public class addcentreActivity extends AppCompatActivity {
  EditText centre,description,phone;
  Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcentre);

        centre = findViewById(R.id.centre);
        description = findViewById(R.id.description);
        phone = findViewById(R.id.phone);
         submit = findViewById(R.id.submit);


        //get spinner from the xml
        Spinner dropdown = findViewById(R.id.location);
        Spinner type = findViewById(R.id.type);
        //create  list of items for the spinner
        String[] items = new String[]{"Location","Nairobi","Mombasa","Naivasha","Kericho","Kisumu","Nakuru"};
        String[] types = new String[]{"Type","Plastic","Paper","Glass","Electronics"};
        //create an sdapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        ArrayAdapter<String> adapterr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,types);

        //Set the spinner adapter to the previously created
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        type.setAdapter(adapterr);
        dropdown.setAdapter(adapter);

        submit.setOnClickListener(v-> {

            String centree = centre.getText().toString();
            String descriptionn = description.getText().toString();
            String phonee = phone.getText().toString();
            String locationn = dropdown.getSelectedItem().toString();
            String typee = type.getSelectedItem().toString();
            if ((locationn.equals("Location")) || (typee.equals("Type"))) {
                Toast.makeText(this, "Specify your location or Type", Toast.LENGTH_SHORT).show();
            }else{
            if ((centree.length() > 1) && (descriptionn.length() > 1) && (phonee.length() > 1)) {
                final ProgressDialog progressDialog = new ProgressDialog(addcentreActivity.this);
                progressDialog.setMessage("Adding ...");
                progressDialog.show();
                final Model model = Model.getInstance(addcentreActivity.this.getApplication());
                model.addcentre(centree, descriptionn, locationn, phonee, typee, new AbstractAPIListener() {
                    @Override
                    public void onAddcentre(User user) {
                        model.setUser(user);
                        if ("success".equals(user.getError())) {
                            Toast.makeText(addcentreActivity.this, "Centre added successfully", Toast.LENGTH_LONG).show();
                            Intent main = new Intent(addcentreActivity.this, userallActivity.class);
                            main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(main);
                        } else {
                            Toast.makeText(addcentreActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });


            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        }
        });


    }
}