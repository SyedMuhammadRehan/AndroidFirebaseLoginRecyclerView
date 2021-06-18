package com.example.plantdiseasedetection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.PersonViewHolder>
{ private  Context ctx;
private ArrayList<myModel> list;
public   myadapter (Context ctx,ArrayList<myModel> list){
    this.ctx=ctx;
    this.list =list;}
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.item, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        myModel model = list.get(position);    holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());    holder.password.setText(model.getPassword());
        holder.rollno.setText(model.getRollno());  holder.skill.setText(model.getSkill());
       // Glide.with(context).load(list.get(position).getImageUri()).into(holder.imgv);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{  TextView name, email, password,skill,rollno;
    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);       name = itemView.findViewById(R.id.name);       email = itemView.findViewById(R.id.email);       password = itemView.findViewById(R.id.password);  rollno=itemView.findViewById(R.id.rollno); skill=itemView.findViewById(R.id.skill);
      }}

}
