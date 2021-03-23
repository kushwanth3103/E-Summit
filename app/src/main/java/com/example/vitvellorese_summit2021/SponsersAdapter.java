package com.example.vitvellorese_summit2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SponsersAdapter extends RecyclerView.Adapter<SponsersAdapter.Sponsers_ViewHolder> {
    ArrayList<Sponsers_details> data_Sp;

    public SponsersAdapter(ArrayList<Sponsers_details> data_sp) {
        this.data_Sp = data_sp;
    }
    @NonNull
    @Override
    public Sponsers_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sponser_item,parent,false);
        return new Sponsers_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Sponsers_ViewHolder holder, int position) {
        Picasso.get().load(data_Sp.get(position).getSponser_Photos()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data_Sp.size();
    }

    public class Sponsers_ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public Sponsers_ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.sponser_images);
        }
    }
}
