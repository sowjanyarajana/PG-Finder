package com.example.login;

//import static com.example.login.Registration.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


public class user_account extends AppCompatActivity{


    TextView name, phone, email, pass;
    FirebaseAuth fauth;
    DatabaseReference databaseReference;
    ImageView imagedummy;

//ActivityReadDataBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        name = findViewById(R.id.My_name);
        phone = findViewById(R.id.My_phone);
        email = findViewById(R.id.My_email);
        pass = findViewById(R.id.My_pass);
        imagedummy = findViewById(R.id.imagedummy);

        try {


            databaseReference = FirebaseDatabase.getInstance().getReference("Registration");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        fauth = FirebaseAuth.getInstance();
                        String userId = fauth.getUid();
                        if (userId != null) {
                            String mname = dataSnapshot.child("" + userId).child("userName").getValue().toString();
                            String mphone = dataSnapshot.child("" + userId).child("phoneNumber").getValue().toString();
                            String memail = dataSnapshot.child("" + userId).child("email").getValue().toString();
                            String mpass = dataSnapshot.child("" + userId).child("password").getValue().toString();
                            name.setText(mname.toString());
                            phone.setText(mphone.toString());
                            email.setText(memail.toString());
                            pass.setText(mpass.toString());
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            phone.setText(e.toString());
            Toast.makeText(this, "Error" + e.toString(), Toast.LENGTH_LONG).show();
        }
        fauth = FirebaseAuth.getInstance();
        String userId = fauth.getUid();

        try {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference dateRef = storageRef.child("Profile/"+userId);
            dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
            {
                @Override
                public void onSuccess(Uri url)
                {
                    //do something with downloadurl
                    /*Glide.with(getApplicationContext())
                            .load(downloadUrl)
                            .placeholder(R.drawable.pgroom)
                            .error(R.drawable.splashimg)
                            .fallback(R.drawable.loginphoto)
                            .into(imagedummy);*/
                    Toast.makeText(getApplicationContext(), "Successfully img loaded"+ url  , Toast.LENGTH_LONG).show();
                   // Toast.makeText(getApplicationContext(),"Successfully uploaded",Toast.LENGTH_LONG).show();
                }
            });/*storageReference = FirebaseStorage.getInstance().getReference("Profile/"+userId);

            File loc= File.createTempFile("tempfile",",jpeg");
            storageReference.getFile(loc)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(loc.getAbsolutePath());
                            imagedummy.setImageBitmap(bitmap);
                            //obj.image.setImageBitmap(bitmap);
                            Toast.makeText(getApplicationContext(),"Error"+loc,Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error"+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    });*/
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error"+e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

