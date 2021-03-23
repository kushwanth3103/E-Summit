package com.example.vitvellorese_summit2021;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class log_in_fragment extends Fragment {
    TextView mail,pass,pass_reset;
    Button log_in;
    FirebaseAuth auth;
    int k1=0,k2=0,k3=0;
    ProgressBar progressBar;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public log_in_fragment() {

    }


    public static log_in_fragment newInstance(String param1, String param2) {
        log_in_fragment fragment = new log_in_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_log_in_fragment, container, false);
        mail=v.findViewById(R.id.mail_id_log);
        pass=v.findViewById(R.id.password_log);
        log_in=v.findViewById(R.id.log_button);
        auth= FirebaseAuth.getInstance();
        pass_reset=v.findViewById(R.id.pass_reset_text);
        progressBar=v.findViewById(R.id.progressBar_log);
        pass_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),password_reset.class));
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(),main_home.class));
                String email_s = mail.getText().toString().trim();
                String password_s = pass.getText().toString().trim();
                k1=0;
                if (TextUtils.isEmpty(email_s)) {
                    mail.setError("Email required");
                    k1++;
                    return;
                }
                k2=0;
                if (TextUtils.isEmpty(password_s)) {
                    pass.setError("Password required");
                    k2++;
                }
                k3=0;
                if (password_s.length() <= 6) {
                    pass.setError("Password must be greater than 6");
                    k3++;
                }
                if(k1==0&&k2==0&&k3==0)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(email_s, password_s).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    if (user.isEmailVerified()) {
                                        SaveSharedPreference.setUserName(getContext(),"person_logged");
                                        progressBar.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(getContext(),main_home.class));
                                    } else {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(getActivity(), "Mail not verified", Toast.LENGTH_SHORT).show();
                                    }
                                    //progressBar.setVisibility(View.GONE);

                                }
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "Error : "+task.getException(), Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
                            }
                        }


                    });
                }
            }
        });

        return v;
    }
}