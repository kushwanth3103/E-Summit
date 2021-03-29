package com.example.vitvellorese_summit2021;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class home_fragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;

    ArrayList<Sponsers_details> data_list_sp;
    SponsersAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public home_fragment() {
        // Required empty public constructor
    }


    public static home_fragment newInstance(String param1, String param2) {
        home_fragment fragment = new home_fragment();
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
        View v=inflater.inflate(R.layout.fragment_home_fragment, container, false);
        firebaseFirestore=FirebaseFirestore.getInstance();
        recyclerView=v.findViewById(R.id.recycler_sp);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data_list_sp=new ArrayList<>();
        firebaseFirestore.collection("Sponsers").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot db:list)
                {
                    Sponsers_details obj=db.toObject(Sponsers_details.class);
                    data_list_sp.add(obj);
                }
                adapter= new SponsersAdapter(data_list_sp);
                recyclerView.setAdapter(adapter);
            }
        });

        return v;
    }
}