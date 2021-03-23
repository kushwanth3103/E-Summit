package com.example.vitvellorese_summit2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class password_reset extends AppCompatActivity {
    EditText pass,confm_pass;
    Button reset;
    ProgressBar progressBar;
    FirebaseAuth auth;
    int count1=0,count2=0,count3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        pass=findViewById(R.id.password_reset);
        confm_pass=findViewById(R.id.confm_password_reset);
        reset=findViewById(R.id.reset);
        progressBar=findViewById(R.id.progressBar_reset1);
        auth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail,confm_mail;
                mail=pass.getText().toString().trim();
                confm_mail=confm_pass.getText().toString().trim();
                count1=0;
                if(mail.isEmpty())
                {
                    pass.setError("Mail address required");
                    pass.requestFocus();
                    count1++;
                }
                count2=0;
                if(confm_mail.isEmpty())
                {
                    confm_pass.setError("Mail address required");
                    pass.requestFocus();
                    count2++;
                }
                count3=0;
                if(!mail.equals(confm_mail) &&count2==0)
                {
                    confm_pass.setError("enter correct mail address");
                    count3++;
                }
                if(count1==0 && count2==0 && count3==0)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    auth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"Check your mail to reset password",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"Try Again something went wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

    }
}