package com.example.ecogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecogreen.api.AbstractAPIListener;

public class replyquiz extends AppCompatActivity {
    Button reply;
    EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replyquiz);
        reply = findViewById(R.id.reply);
        answer = findViewById(R.id.answer);

        Intent test = getIntent();
        String id = test.getStringExtra("id");

        reply.setOnClickListener(v->{
            String answerr = answer.getText().toString();
            if(answerr.length() > 0){
                final ProgressDialog progressDialog = new ProgressDialog(replyquiz.this);
                progressDialog.setMessage("Submitting your answer ...");
                progressDialog.show();
                final Model model = Model.getInstance(replyquiz.this.getApplication());
                model.postreply(answerr,id, new AbstractAPIListener() {
                    @Override
                    public void onPostreply(User user) {
                        model.setUser(user);
                        if ("Success".equals(user.getError())) {
                            Toast.makeText(replyquiz.this, "Reply sent", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(replyquiz.this,getquizes.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(replyquiz.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });


            }else{
                Toast.makeText(this,"No answer provided",Toast.LENGTH_SHORT).show();
            }
        });
    }
}