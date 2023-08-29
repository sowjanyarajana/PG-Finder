/*
package com.example.login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String  TAG="TAG";
    EditText username, email, password, conformpwd, phonenum;
    Button register;
    FirebaseFirestore fstore;
    String userid;


    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usname);
        email = findViewById(R.id.uemail);
        password = findViewById(R.id.upassword);
        conformpwd = findViewById(R.id.ucnfpwd);
        register = findViewById(R.id.btn_Register);
        phonenum = findViewById(R.id.phonenum);


        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if (fauth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), user_login.class);
            startActivity(intent);
           // finish();
        }


    }


    public void userlogin(View v) {
        Intent intent = new Intent(getApplicationContext(), user_login.class);
        startActivity(intent);
        finish();
    }

    public void userRegister(View view) {
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
                    Toast.makeText(MainActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}*/
package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


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
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[@#$%^&])" +
            "(?=\\S+$)" +
            ".{8,}" +
            "$");

    public static final String TAG = "TAG";
    EditText username, email, password, conformpwd, phonenum;
    Button register;
    Spinner LType;
    FirebaseFirestore fstore;
    String userid;
    DatabaseReference databaseReference;

    FirebaseAuth fauth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.usname);
        email = findViewById(R.id.uemail);
        password = findViewById(R.id.upassword);
        conformpwd = findViewById(R.id.ucnfpwd);
        register = findViewById(R.id.btn_Register);
        phonenum = findViewById(R.id.phonenum);
        LType = (Spinner) findViewById(R.id.LoginType);


        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

       /*if (fauth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), main_page.class);
            startActivity(intent);
           // finish();
        }*/


    }


    public void userlogin(View v) {
        Intent intent = new Intent(getApplicationContext(), user_login.class);
        startActivity(intent);
        finish();
    }

    public void userRegister(View view) {
        try {


            String susername = username.getText().toString();
            String semail = email.getText().toString().trim();
            String spassword = password.getText().toString();
            String sconformpwd = conformpwd.getText().toString();
            String sphonenum = phonenum.getText().toString();
            String LoginType = LType.getSelectedItem().toString();

            if (TextUtils.isEmpty(susername)) {
                username.setError("Enter User Name");
                return;
            }

            if (TextUtils.isEmpty(sphonenum)) {
                phonenum.setError("Enter phone number");
                return;
            } else if (!Patterns.PHONE.matcher(sphonenum).matches()) {
                phonenum.setError("Enter a valid phone number");
                return;
            }

            if (TextUtils.isEmpty(semail)) {
                email.setError("Enter email");
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(semail).matches()) {
                email.setError("Is is not a valid email address ");
                return;
            }
            if (TextUtils.isEmpty(spassword)) {
                password.setError("Enter password");
                return;
            } else if (!PASSWORD_PATTERN.matcher(spassword).matches()) {
                password.setError("Enter A Strong password");
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
                                databaseReference = FirebaseDatabase.getInstance().getReference("Registration");
                                String strid = fauth.getUid();
                                RegistrationClass Ob1 = new RegistrationClass(strid, susername, sphonenum, semail, spassword, LoginType);
                                databaseReference.child(strid).setValue(Ob1);
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
                        user.put("LoginType", LoginType);
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
                        finish();
                    } else {
                        Toast.makeText(Registration.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(Registration.this, " " + e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}