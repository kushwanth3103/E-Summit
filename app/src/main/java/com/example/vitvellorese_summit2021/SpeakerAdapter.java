package com.example.vitvellorese_summit2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.ViewHolder_class> {
    ArrayList<speakers_details> data;

    public SpeakersAdapter(ArrayList<speakers_details> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder_class onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.speaker_item,parent,false);
        return new ViewHolder_class(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_class holder, int position) {
        holder.name_h.setText(data.get(position).getName());
        holder.pos_h.setText(data.get(position).getPosition());
        String img=data.get(position).getPhoto();
        //holder.pos_h.setText(img);
        Picasso.get().load(img).resize(150,150).into(holder.image);
        //holder.image.setImageURI(data.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder_class extends RecyclerView.ViewHolder
    {
        TextView name_h,pos_h;
        ImageView image;
        public ViewHolder_class(@NonNull View itemView) {
            super(itemView);
            name_h=itemView.findViewById(R.id.name);
            pos_h=itemView.findViewById(R.id.Pos_detail);
            image=itemView.findViewById(R.id.photo_s);
        }
    }


}