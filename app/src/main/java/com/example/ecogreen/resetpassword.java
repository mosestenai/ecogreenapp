package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecogreen.api.AbstractAPIListener;

public class resetpassword extends AppCompatActivity {
EditText email;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(v-> {
            String emaill = email.getText().toString();

            if (emaill.length() > 0){
                final ProgressDialog progressDialog = new ProgressDialog(resetpassword.this);
            progressDialog.setMessage("Submitting request ...");
            progressDialog.show();
            //final Model model = Model.getInstance(requireActivity().getApplication());
            final Model model = Model.getInstance(resetpassword.this.getApplication());
            model.resetpassword(emaill, new AbstractAPIListener() {
                @Override
                public void onResetpassword(User user) {
                    model.setUser(user);
                    if ("success".equals(user.getError())) {
                        Toast.makeText(resetpassword.this, "Reset link has been sent to your email", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(resetpassword.this, user.getError(), Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }


            });
        }else{
                Toast.makeText(this,"Email field is empty",Toast.LENGTH_SHORT).show();
            }
        });
    }
}