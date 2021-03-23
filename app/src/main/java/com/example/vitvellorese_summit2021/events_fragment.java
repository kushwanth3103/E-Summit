package com.example.vitvellorese_summit2021;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class events_fragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    ArrayList<event_details> data_list_event;
    event_Adapter adapter1;
    ProgressBar progressBar;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public events_fragment() {

    }


    public static events_fragment newInstance(String param1, String param2) {
        events_fragment fragment = new events_fragment();
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
        View v=inflater.inflate(R.layout.fragment_events_fragment, container, false);
        firebaseFirestore=FirebaseFirestore.getInstance();
        recyclerView=v.findViewById(R.id.event_recycler);
        progressBar=v.findViewById(R.id.progressBar_event);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data_list_event=new ArrayList<>();
        firebaseFirestore.collection("events").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot db:list)
                {
                    event_details obj=db.toObject(event_details.class);
                    data_list_event.add(obj);
                }
                adapter1=new event_Adapter(data_list_event);
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(adapter1);
            }
        });

        return v;

    }

}