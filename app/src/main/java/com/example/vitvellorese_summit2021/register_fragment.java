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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class register_fragment extends Fragment {
    TextView mail_id,pass,name,uni_name,reg_id;
    CheckBox vitian,non_vitian;
    Button register;
    FirebaseAuth firebaseAuth;
    int flag1,flag2;
    String user_id;
    FirebaseFirestore firebaseFirestore;
    Map<String,Object> participants_details=new HashMap<>();
    int k1=0,k2=0,k3=0,k4=0,k5=0,k6=0,v1=0,v2=0;
    ProgressBar progressBar;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public register_fragment() {

    }

    public static register_fragment newInstance(String param1, String param2) {
        register_fragment fragment = new register_fragment();
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
        View v=inflater.inflate(R.layout.fragment_register_fragment, container, false);
        mail_id=v.findViewById(R.id.mail_id_log);
        pass=v.findViewById(R.id.password);
        name=v.findViewById(R.id.name);
        reg_id=v.findViewById(R.id.reg_id);
        uni_name=v.findViewById(R.id.uni_name);
        vitian=v.findViewById(R.id.vitian);
        non_vitian=v.findViewById(R.id.non_vitian);
        register=v.findViewById(R.id.log_button);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressBar=v.findViewById(R.id.progressBar_register);
        vitian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    v1++;
                    reg_id.setVisibility(View.VISIBLE);
                    uni_name.setVisibility(View.INVISIBLE);
                    non_vitian.setVisibility(View.INVISIBLE);
                    flag1=1;
                }
                else {
                    v1=0;
                    non_vitian.setVisibility(View.VISIBLE);
                    reg_id.setVisibility(View.INVISIBLE);
                    flag1=0;
                }
            }
        });
        non_vitian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    v2++;
                    uni_name.setVisibility(View.VISIBLE);
                    reg_id.setVisibility(View.INVISIBLE);
                    vitian.setVisibility(View.INVISIBLE);
                    flag2=1;
                }
                else
                {
                    v2=0;
                    uni_name.setVisibility(View.INVISIBLE);
                    vitian.setVisibility(View.VISIBLE);
                    flag2=0;
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail=mail_id.getText().toString().trim();
                String password=pass.getText().toString().trim();
                final String person_name=name.getText().toString().trim();
                final String reg=reg_id.getText().toString().trim();
                final String university=uni_name.getText().toString().trim();
                k1=0;
                if(TextUtils.isEmpty(mail))
                {
                    mail_id.setError("Email required");
                    k1++;
                    return;
                }
                k2=0;
                if(TextUtils.isEmpty(password) &&k1==0)
                {
                    pass.setError("Password required");
                    k2++;
                }
                k3=0;
                if(password.length()<=6)
                {
                    pass.setError("Password must be greater than 6 char");
                    k3++;
                }
                k4=0;
                if(TextUtils.isEmpty(person_name))
                {
                    name.setError("Please enter name");
                    k4++;
                }
                if(flag1==1)
                {
                    k5=0;
                    if(TextUtils.isEmpty(reg))
                    {
                        reg_id.setError("Reg no required");
                        k5++;
                        return;
                    }
                }
                if(flag2==1)
                {
                    k6=0;
                    if(TextUtils.isEmpty(university))
                    {
                        uni_name.setError("Universitiy name is required");
                        k6++;
                    }
                }
                if(k1==0 && k2==0 &&k3==0 &&k4==0&&k5==0&k6==0 &&(v1==1 ||v2==1))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    user.sendEmailVerification();
                                }
                                DocumentReference documentReference = null;
                                user_id=firebaseAuth.getCurrentUser().getUid();
                                documentReference=firebaseFirestore.collection("Participants").document(user_id);
                                participants_details.put("Person_Name",person_name);
                                participants_details.put("Person_Mail",mail);
                                participants_details.put("Reg_no",reg);
                                participants_details.put("University_name",university);
                                documentReference.set(participants_details);
                                //startActivity(new Intent(getContext(),main_home.class));
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(),"Successfully Registered Please VERIFY MAIL To LOGIN",Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
                            }
                            else
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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