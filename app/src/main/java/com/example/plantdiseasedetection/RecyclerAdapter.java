package com.example.plantdiseasedetection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String Tag = "RecyclerView";
private Context mContext;
    private ArrayList<Model> list;
private  RecyclerAdapter recyclerAdapter;
    public RecyclerAdapter(Context mContext, ArrayList<Model> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.rollno.setText(list.get(position).getRollno());
        holder.skill.setText(list.get(position).getSkill());

        Glide.with(mContext).load(list.get(position).getImageUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, email, rollno, skill;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.nam);
            email=itemView.findViewById(R.id.emaill);
            rollno=itemView.findViewById(R.id.roll);
            skill=itemView.findViewById(R.id.skill);

        }
    }
}