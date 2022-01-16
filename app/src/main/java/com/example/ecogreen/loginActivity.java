package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecogreen.api.AbstractAPIListener;

public class loginActivity extends AppCompatActivity {
EditText username,password;
Button login,back,signup,resetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        back = findViewById(R.id.back);
        signup = findViewById(R.id.signup);
        resetpass = findViewById(R.id.resetpass);

        //back button
        back.setOnClickListener(v->{
            Intent intent = new Intent(loginActivity.this,MainActivity.class);

            //clear task
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        //signup button
        signup.setOnClickListener(v->{
            Intent intent = new Intent(loginActivity.this,registerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        //reset password button
        resetpass.setOnClickListener(v->{
            Intent intent = new Intent(loginActivity.this,resetpassword.class);
            startActivity(intent);
        });

        //take user details and compare if a user exist of the same username and pasword
        login.setOnClickListener(v->{

            //convert users inputs into string
            String emaill = username.getText().toString();
            String passwordd =  password.getText().toString();

            if(emaill.length()   > 1){

                //show progress bar
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                //final Model model = Model.getInstance(requireActivity().getApplication());
                final Model model = Model.getInstance(loginActivity.this.getApplication());
                model.login(emaill,passwordd, new AbstractAPIListener() {
                    @Override
                    public void onLogin(User user){
                        model.setUser(user);
                        switch (user.getPermission()) {
                            case "user": {
                                Intent intent = new Intent(loginActivity.this, userallActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }
                            case "admin": {
                                Intent intent = new Intent(loginActivity.this, adminActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }
                            case "shop": {
                                Intent intent = new Intent(loginActivity.this, ownershopActivity.class);
                                intent.putExtra("username",emaill);
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }
                            default:
                                Toast.makeText(loginActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                break;
                        }


                    }
                });


            }

            else if((emaill.length() & password.length()) == 0 ){

                //display user an error when fileds are empty
                Toast.makeText(loginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();


            }

        });
    }
}