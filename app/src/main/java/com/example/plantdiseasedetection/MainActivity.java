package com.example.plantdiseasedetection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private String user;
    private Button logout,pass;
    private ImageView img;
    private Uri imageUri;
    private RecyclerView rv;
    FirebaseDatabase root=FirebaseDatabase.getInstance();
    DatabaseReference ref=root.getReference("users");
StorageReference reference = FirebaseStorage.getInstance().getReference();
    myadapter adp;   ArrayList<myModel> list;    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        pass=findViewById(R.id.updateps);
        manager = new LinearLayoutManager(this);       list  = new ArrayList<>();      rv.setLayoutManager(manager);      Fetectdata();
logout=findViewById(R.id.logout);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, changepass.class));
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
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }



        });

    }

    private void Fetectdata() {
        ref
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot snapshot1 : snapshot.getChildren()){
                            String name = snapshot1.child("name").getValue().toString();
                            String email = snapshot1.child("email").getValue().toString();
                            String password = snapshot1.child("password").getValue().toString();
                            String rollno = snapshot1.child("rollno").getValue().toString();
                            String skill = snapshot1.child("imageUri").getValue().toString();
                            //String imageUri = snapshot1.child("imageUri").getValue().toString();
                            list.add(new myModel(name, email, password,rollno,skill));                  }
                        adp = new myadapter(MainActivity.this, list);                   rv.setAdapter(adp);               }
                        @Override               public void onCancelled(DatabaseError error) {                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT);               }           });}



}