package com.example.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


public class delete_pg extends AppCompatActivity {


    EditText txt_pgname, txtinfo2;
    TextView txtuid;
    Button btndelete, btnauth;


    private FirebaseAuth fauth;
    private FirebaseUser firebaseUser;
    private static final String TAG = "delete_pg";
    String uspwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityDeletePgBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_delete_pg);

        try {
            txt_pgname = findViewById(R.id.txtpgname);
            btndelete = findViewById(R.id.btndelete);
            btnauth = findViewById(R.id.btnauth);
            txtuid = findViewById(R.id.txtuid);
            txtinfo2 = findViewById(R.id.txtinfo2);

            //disable delete button
            btndelete.setEnabled(false);

            fauth = FirebaseAuth.getInstance();
            firebaseUser = fauth.getCurrentUser();
            if (firebaseUser.equals("")) {
                Toast.makeText(delete_pg.this, "Something wrong", Toast.LENGTH_SHORT).show();

            } else {
                reauthenticate(firebaseUser);
            }
        
        
       /*
        reference.addValueEventListener(new ValueEventListener() {





                @Override
                public void onDataChange (@NonNull DataSnapshot snapshot){
                if (snapshot.exists()) {
                    FirebaseUser currentU = FirebaseAuth.getInstance().getCurrentUser();
                    String uId = currentU.getUid();
                    txtuid.setText(uId);
                    //fauth = FirebaseAuth.getInstance();
                    // String userId = fauth.getUid();
                    if (uId != null) {
                        String pgname = snapshot.child("" + uId).child("pg_Name").getValue().toString();
                        txt_pgname.setText(pgname);
                        //binding.txtpgname.setText("pgname");
                    }


                }
            }

                @Override
                public void onCancelled (@NonNull DatabaseError error){

            }
            });



        btndelete.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                //String uId = binding.txtPgname.getText().toString();
                fauth = FirebaseAuth.getInstance();
                String id = fauth.getUid();
                if (id != null) {
                    deleteData(id);

                } else {

                    Toast.makeText(delete_pg.this, "Enter  Your PG Name ", Toast.LENGTH_SHORT).show();
                }

            }
            });*/

        } catch (Exception e) {
            Toast.makeText(delete_pg.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void reauthenticate(FirebaseUser firebaseUser) {
        btnauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uspwd = txtuid.getText().toString();
                if (TextUtils.isEmpty(uspwd)) {
                    Toast.makeText(delete_pg.this, "Password needed ", Toast.LENGTH_SHORT).show();
                    txtuid.setError("Please enter password");
                    txtuid.requestFocus();
                } else {
                    AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(), uspwd);
                    firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                txtuid.setEnabled(false);
                                btnauth.setEnabled(false);
                                btndelete.setEnabled(true);

                                txtinfo2.setText("Your are verified ,now you can delete your PG data");
                                Toast.makeText(delete_pg.this, "Password  Authenticated ", Toast.LENGTH_SHORT).show();

                                btndelete.setBackgroundTintList(ContextCompat.getColorStateList(delete_pg.this, R.color.darkgreen));
                                btndelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showAlertDialog();
                                    }
                                });

                            } else {
                                try {
                                    throw task.getException();
                                } catch (Exception e) {
                                    Toast.makeText(delete_pg.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });
                }

            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(delete_pg.this);
        builder.setTitle("Delete PG Data");
        builder.setMessage("Do you really want to delete your data? This action is irreversible!!");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteData(firebaseUser);
            }
        });

        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(delete_pg.this, user_account.class);
                startActivity(intent);
                finish();
            }
        });
        //create alert dialog
        AlertDialog alertDialog = builder.create();
        //change continue button colour
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
            }
        });

        //show alert box
        alertDialog.show();
    }

    private void deleteData(FirebaseUser firebaseUser) {
        try {


            DatabaseReference reference;
            reference = FirebaseDatabase.getInstance().getReference("AddPG");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        fauth = FirebaseAuth.getInstance();
                        String userId = fauth.getUid();
                        if (userId != null) {
                            String PGN = dataSnapshot.child("" + userId).child("pg_Name").getValue().toString();
                            txt_pgname.setText(PGN);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            reference.child(firebaseUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(delete_pg.this, "Data deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), user_account.class);
                    startActivity(intent);
                    Log.d(TAG, "On success User PGdata got deleted!! ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, e.getMessage());
                    Toast.makeText(delete_pg.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(delete_pg.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}