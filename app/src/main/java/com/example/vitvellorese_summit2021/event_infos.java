package com.example.vitvellorese_summit2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class event_infos extends AppCompatActivity {
    String value;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    TextView title,type,description,date,pa_type;
    Button register;
    ImageView icons;
    Map<String,Object> registration_details=new HashMap<>();
    String event_name;
    String user_id;
    FirebaseAuth firebaseAuth;
    String name,reg_no,mail,university,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_infos);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent i=getIntent();
        value=i.getStringExtra("value");
        title=findViewById(R.id.title_s);
        type=findViewById(R.id.type_s);
        description=findViewById(R.id.des_s);
        date=findViewById(R.id.date_s);
        pa_type=findViewById(R.id.pa_type_s);
        register=findViewById(R.id.register_s);
        icons=findViewById(R.id.icon_s);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference=firebaseFirestore.collection("events").document(value.trim());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null) {
                    event_name =documentSnapshot.getString("title");
                    title.setText(documentSnapshot.getString("title"));
                    type.setText(documentSnapshot.getString("type"));
                    description.setText(documentSnapshot.getString("matter"));
                    date.setText(documentSnapshot.getString("date_detail"));
                    pa_type.setText(documentSnapshot.getString("participation_type"));
                    link=documentSnapshot.getString("link");
                    if(link.equals(""))
                    {
                        register.setText("Comming Soon");
                    }
                    Picasso.get().load(documentSnapshot.getString("icon")).into(icons);
                }
           }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(link.equals(""))
                {

                }
                else
                {
                    Uri uri = Uri.parse(link);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
        /*user_id= firebaseAuth.getCurrentUser().getUid();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference1=firebaseFirestore.collection(event_name).document(user_id);
                DocumentReference documentReference3=firebaseFirestore.collection("Participants").document(user_id);

                documentReference3.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if(documentSnapshot!=null)
                        {
                            name=documentSnapshot.getString("Person_Name");
                            mail=documentSnapshot.getString("Person_Mail");
                            reg_no=documentSnapshot.getString("Reg_no");
                            university=documentSnapshot.getString("University_name");
                        }
                    }
                });
            }
        });*/
    }
}