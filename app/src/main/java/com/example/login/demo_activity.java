package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class demo_activity extends AppCompatActivity {

    //public static final String  TAG="TAG";
    EditText username, email, password, conformpwd, phonenum;
    Button register;
    //FirebaseFirestore fstore;
    //String userid;



   // FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
public void process(View view){
            username = findViewById(R.id.usname);
            email = findViewById(R.id.uemail);
            password = findViewById(R.id.upassword);
            conformpwd = findViewById(R.id.ucnfpwd);
            register = findViewById(R.id.btn_Register);
            phonenum = findViewById(R.id.phonenum);

            FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root=db.getReference();
    root.setValue(username.getText().toString());
    username.setText("");
    Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_LONG).show();
        }







    public void userlogin(View v) {
        Intent intent = new Intent(getApplicationContext(), user_login.class);
        startActivity(intent);
        finish();
    }

    /*public void userRegister(View view) {
        final String susername = username.getText().toString();
        final String semail = email.getText().toString().trim();
        String spassword = password.getText().toString();
        String sconformpwd = conformpwd.getText().toString();
        final String sphonenum = phonenum.getText().toString();

        if (TextUtils.isEmpty(susername)) {
            username.setError("Enter User Name");
            return;
        }

        if (TextUtils.isEmpty(sphonenum)) {
            phonenum.setError("Enter phone number");
            return;
        }

        if (TextUtils.isEmpty(semail)) {
            email.setError("Enter email");
            return;
        }
        if (TextUtils.isEmpty(spassword)) {
            password.setError("Enter password");
            return;
        }
        if (TextUtils.isEmpty(sconformpwd)) {
            conformpwd.setError("Enter Password");
            return;
        }

        fauth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser fuser = fauth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "On Failure : Email not sent" + e.getMessage());
                        }
                    });
                    Toast.makeText(getApplicationContext(), "User Account Successfully Created", Toast.LENGTH_SHORT).show();
                    userid = fauth.getCurrentUser().getUid();
                    DocumentReference documentReference = fstore.collection("user").document(userid);
                    Map<String, Object> user = new HashMap<>();
                    user.put("fname", susername);
                    user.put("phone", sphonenum);
                    user.put("email", semail);
                    user.put("password", spassword);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "OnSuccess:User Profile Created" + userid);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Onfailur" + e.toString());
                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), user_login.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(demo_activity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }*/
}