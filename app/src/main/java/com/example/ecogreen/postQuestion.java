package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecogreen.api.AbstractAPIListener;

public class postQuestion extends AppCompatActivity {
Button submit;
EditText question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        question = findViewById(R.id.question);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(v->{
            String questionn = question.getText().toString();
            if(questionn.length() > 0 ){
                final ProgressDialog progressDialog = new ProgressDialog(postQuestion.this);
                progressDialog.setMessage("Submitting your question ...");
                progressDialog.show();
                final Model model = Model.getInstance(postQuestion.this.getApplication());
                model.postquiz(questionn, new AbstractAPIListener() {
                    @Override
                    public void onPostquiz(User user) {
                        model.setUser(user);
                        if ("Success".equals(user.getError())) {
                            Toast.makeText(postQuestion.this, "question sent", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(postQuestion.this,faqsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(postQuestion.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });

            }else {
                Toast.makeText(this,"Type your quiz",Toast.LENGTH_SHORT).show();
            }

        });
    }
}