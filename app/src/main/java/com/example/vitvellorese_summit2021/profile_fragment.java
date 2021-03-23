package com.example.vitvellorese_summit2021;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class profile_fragment extends Fragment {
    TextView name,mail_id,uni_name,reg_no,r;
    Button log_out;
    ImageView insta,fb,linkdin,twitter;
    String user_id;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public profile_fragment() {

    }

    public static profile_fragment newInstance(String param1, String param2) {
        profile_fragment fragment = new profile_fragment();
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
        View v=inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        name=v.findViewById(R.id.name_text);
        mail_id=v.findViewById(R.id.mail_text);
        reg_no=v.findViewById(R.id.reg_text);
        uni_name=v.findViewById(R.id.uni_text);
        log_out=v.findViewById(R.id.log_out_button);
        insta=v.findViewById(R.id.insta);
        linkdin=v.findViewById(R.id.linkden);
        fb=v.findViewById(R.id.fb);
        r=v.findViewById(R.id.textView18);
        DocumentReference documentReference=null;
        twitter=v.findViewById(R.id.twitter);firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            user_id= firebaseAuth.getCurrentUser().getUid();
        }
        if(user_id!=null)
        {
            documentReference=firebaseFirestore.collection("Participants").document(user_id);
            //if(documentReference!=null)
            //{
                documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (documentSnapshot != null) {
                            name.setText(documentSnapshot.getString("Person_Name"));
                            mail_id.setText(documentSnapshot.getString("Person_Mail"));
                            if(documentSnapshot.getString("Reg_no").isEmpty())
                            {
                                uni_name.setText(documentSnapshot.getString("University_name"));
                                reg_no.setVisibility(View.INVISIBLE);
                                r.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                reg_no.setText(documentSnapshot.getString("Reg_no"));
                                uni_name.setText("Vellore Institute Of Technology");
                            }
                        }
                    }
                });
            //}
            /*else {
               documentReference=firebaseFirestore.collection("Participants_Non_Vitians").document(user_id);
                documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (documentSnapshot != null) {
                            name.setText(documentSnapshot.getString("Person_Name"));
                            mail_id.setText(documentSnapshot.getString("Person_Mail"));
                            uni_name.setText(documentSnapshot.getString("University_Name"));
                            reg_no.setVisibility(View.INVISIBLE);
                            r.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }*/
        }
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Successfully logged out",Toast.LENGTH_SHORT).show();
                SaveSharedPreference.clearUserName(getContext());
                startActivity(new Intent(getActivity(),log_in.class));
            }
        });

        return v;
    }
}