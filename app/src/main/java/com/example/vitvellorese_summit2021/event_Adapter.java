package com.example.vitvellorese_summit2021;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

class event_Adapter extends RecyclerView.Adapter<event_Adapter.ViewHolder_class_event> {
    ArrayList<event_details> data1;
    int k;
    public event_Adapter(ArrayList<event_details> data1) {
        this.data1 = data1;
    }

    @NonNull
    @Override
    public ViewHolder_class_event onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        return new ViewHolder_class_event(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_class_event holder, int position) {
        holder.title_name.setText(data1.get(position).getTitle());
        holder.des.setText(data1.get(position).getMatter());
        String img=data1.get(position).getIcon();

        holder.date.setText(data1.get(position).getDate());
        //holder.pos_h.setText(img);
        Picasso.get().load(img).into(holder.icon);
        //holder.image.setImageURI(data.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return data1.size();
    }


    class ViewHolder_class_event extends RecyclerView.ViewHolder
    {
        TextView title_name,des,date;
        ImageView icon;
        Button show;
        public ViewHolder_class_event(@NonNull final View itemView) {
            super(itemView);
            title_name=itemView.findViewById(R.id.title_event);
            des=itemView.findViewById(R.id.description_event);
            date=itemView.findViewById(R.id.date_text);
            icon=itemView.findViewById(R.id.event_icon);
            show=itemView.findViewById(R.id.show_more);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    k=getAdapterPosition();
                    Intent intent=new Intent(v.getContext(),event_infos.class);
                    intent.putExtra("value",k+"");
                    v.getContext().startActivity(intent);
                }

            });
        }

    }

}