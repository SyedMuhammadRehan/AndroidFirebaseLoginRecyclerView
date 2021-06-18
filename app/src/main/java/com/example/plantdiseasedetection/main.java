package com.example.plantdiseasedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class main extends AppCompatActivity {
RecyclerView recyclerView;
private  RecyclerAdapter adap;
    private Button logout,pass;
private DatabaseReference ref;
private ArrayList<Model> list;
    private FirebaseAuth auth;
    private String user;
private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        logout=findViewById(R.id.logout);
        pass=findViewById(R.id.updateps);



            recyclerView =findViewById(R.id.recycle);
        FirebaseUser userr=FirebaseAuth.getInstance().getCurrentUser();
        String id=userr.getUid();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ref= FirebaseDatabase.getInstance().getReference();
        list =new ArrayList<>();
        GetDatafromfirebase();
        CleareAll();;

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main.this, ResetPassword.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();

                editor.commit();
                auth = FirebaseAuth.getInstance();
                user=FirebaseAuth.getInstance().getCurrentUser().getUid();

                auth.signOut();
                Intent intent = new Intent(main.this, Login.class);
                startActivity(intent);
                finish();
            }



        });

    }


    private void GetDatafromfirebase() {


        Query query= ref.child("users");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot ) {
                CleareAll();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Model model=new Model();
                    model.setImageUri(snapshot. child("skill").getValue().toString());

                    model.setEmail(snapshot.child("name").getValue().toString());

                    model.setName(snapshot.child("email").getValue().toString());
                    model.setRollno(snapshot.child("password").getValue().toString());

                    model.setSkill(snapshot.child("rollno").getValue().toString());
list.add(model);
                }
           adap=new RecyclerAdapter(getApplicationContext(),list);
                recyclerView
                        .setAdapter(adap);
                adap.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void CleareAll(){
        if(list !=null){
            list.clear();
            if(adap!=null){
                adap.notifyDataSetChanged();
            }
        }
        list= new ArrayList<>();
    }

}