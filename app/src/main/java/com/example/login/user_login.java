package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_login extends AppCompatActivity {

    TextView signin;
    Button ulogin;
    EditText uloginEmail, uloginpwd;

    FirebaseAuth fauth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        signin = findViewById(R.id.usignin);
        ulogin = findViewById(R.id.ubtn_login);
        uloginEmail = findViewById(R.id.ulogin_email);
        uloginpwd = findViewById(R.id.ulogin_password);


        fauth = FirebaseAuth.getInstance();
        if (fauth.getCurrentUser()!=null)
        { startActivity(new Intent(getApplicationContext(),user_homepage.class));
            finish();
        }

        ulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = uloginEmail.getText().toString();
                String password = uloginpwd.getText().toString();

                if (TextUtils.isEmpty(useremail)) {
                    uloginEmail.setError("Enter Email");
                }

                if (TextUtils.isEmpty(password)) {
                    uloginpwd.setError("Enter password");
                }


                fauth.signInWithEmailAndPassword(useremail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            databaseReference = FirebaseDatabase.getInstance().getReference("Registration");
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        fauth = FirebaseAuth.getInstance();
                                        String userId = fauth.getUid();
                                        if (userId != null) {
                                            String pgtype = dataSnapshot.child("" + userId).child("loginType").getValue().toString();
                                            if (pgtype.equals("User")) {
                                                Toast.makeText(getApplicationContext(), "Login Successful as user", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), user_homepage.class));
                                            } else if (pgtype.equals("Owner")) {
                                                Toast.makeText(getApplicationContext(), "Login Successful as owner", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), user_homepage.class));
                                            }


                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }


                                // startActivity(new Intent(getApplicationContext(),main_page.class));

                            });
                        }


                        signin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);


                            }
                        });

                    }
                });
            }
        });
    }
}