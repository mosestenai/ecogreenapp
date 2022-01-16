package com.example.ecogreen;

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

public class registerActivity extends AppCompatActivity {
Button register,login,back;
EditText username,email,password_1,password_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getting inputs
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password_1 = findViewById(R.id.password1);
        password_2 = findViewById(R.id.password2);
        //buttons
        back = findViewById(R.id.back);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);


        //get spinner from the xml
        Spinner dropdown = findViewById(R.id.spinner1);
        //create  list of items for the spinner
        String[] items = new String[]{"Location","Nairobi","Mombasa","Naivasha","Kericho","Kisumu","Nakuru"};
        //create an sdapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //Set the spinner adapter to the previously created
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);

        //action after register button is clicked
        register.setOnClickListener(v-> {
            String location = dropdown.getSelectedItem().toString();

            String usernamee = username.getText().toString();
            String emaill = email.getText().toString();
            String password1 = password_1.getText().toString();
            String password2 = password_2.getText().toString();


            if (location.equals("Location")) {
                Toast.makeText(this, "Specify your location", Toast.LENGTH_SHORT).show();
            } else{
                if (!password1.equals(password2)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {

                if (usernamee.length() > 1) {
                    final ProgressDialog progressDialog = new ProgressDialog(registerActivity.this);
                    progressDialog.setMessage("Registering ...");
                    progressDialog.show();
                    //final Model model = Model.getInstance(requireActivity().getApplication());
                    final Model model = Model.getInstance(registerActivity.this.getApplication());
                    model.register(usernamee, emaill, location, password2, new AbstractAPIListener() {
                        @Override
                        public void onRegister(User user) {
                            model.setUser(user);
                            if ("user".equals(user.getPermission())) {
                                Intent intent = new Intent(registerActivity.this, userallActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(registerActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();


                        }
                    });


                } else if ((usernamee.length() & password_1.length()) == 0) {
                    Toast.makeText(registerActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();


                }
            }
        }

        });

        //action after login button clicked
        login.setOnClickListener(v->{
            Intent intent = new Intent(registerActivity.this,loginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        //action after back button is clicked
        back.setOnClickListener(v->{
            Intent intent = new Intent(registerActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });




    }
}