package com.example.plantdiseasedetection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;


public class Register extends AppCompatActivity {


    private ImageView img;
    private Uri imageUri;

    StorageReference reference = FirebaseStorage.getInstance().getReference();
    RadioGroup rg;
    RadioButton btn,male,female;
    private RadioGroup radioSexGroup;
    private RadioButton radioButton;
    private EditText inputEmail, inputPassword,namee,rolll,cpass, skill;
    private Button btnSignIn, btnSignUp, btnResetPassword ;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    FirebaseDatabase root=FirebaseDatabase.getInstance();
    DatabaseReference ref=root.getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        img=findViewById(R.id.imageView);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
namee= (EditText) findViewById(R.id.name);
male=findViewById(R.id.male);
female=findViewById(R.id.female);
rolll= (EditText) findViewById(R.id.rollno);
cpass=findViewById(R.id.cpassword);
skill=findViewById(R.id.skill);
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
//        rg = findViewById(R.id.rdgrp);
//        radioSexGroup=(RadioGroup)findViewById(R.id.rdgrp);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }

        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rdgrp);
//
//// get selected radioButton from radioGroup
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//
//// find the radioButton by returned id
//                radioButton = (RadioButton) findViewById(selectedId);
//
//// radioButton text
//                String radiovalue = (String) radioButton.getText();

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String cp=cpass.getText().toString().trim();

                if (namee.length() < 4) {
                    Toast.makeText(getApplicationContext(), "Name too short, enter minimum 4 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rolll.length() < 6) {
                    Toast.makeText(getApplicationContext(), "RollNo too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (skill.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Skill too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(cp) ) {
                    Toast.makeText(getApplicationContext(), "Password did not match, enter right password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(imageUri == null){
                    Toast.makeText(Register.this, "Plz Select image", Toast.LENGTH_SHORT);}


//                    HashMap<String,String> map=new HashMap<>();
//                map.put("email","syedrehan@gmail.com");
//                map.put("password", "1235678");
//                ref.push().setValue(map);


                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    uploadInFirebase(imageUri);

//                                    HashMap<String,String> map=new HashMap<>();
//                                    map.put("email",email);
//                                    map.put("password", password);
//                                   ref.push().setValue(map);
                                    startActivity(new Intent(Register.this, main.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);  if(requestCode == 2 && resultCode == RESULT_OK &&  data != null){
            imageUri = data.getData();

        img.setImageURI(imageUri) ;  } }

    private void uploadInFirebase(Uri uri)
    {StorageReference fileref = reference.child(auth.getCurrentUser().getUid());
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override           public void onSuccess(Uri uri) {
                        String email = inputEmail.getText().toString().trim();
                        String password = inputPassword.getText().toString().trim();

                        String name=namee.getText().toString();

String r =rolll.getText().toString().trim();
String s=skill.getText().toString().trim();
//                        String data;
//                        if (male.isChecked()) {
//                           data="Male";
//                        } else if (female.isChecked()) {
//                            data="Female";
//                        }

                        Model model = new Model(password,email,name,r,s,uri.toString());
                        String modelid = ref.push().getKey();
                        ref.child(modelid).setValue(model);
                        Toast.makeText(Register.this, "uploaded Successfully", Toast.LENGTH_SHORT);
                    }        });
            }}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
            }}).addOnFailureListener(new OnFailureListener() {
            @Override   public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "uploading fail", Toast.LENGTH_SHORT);    }});   }
}
