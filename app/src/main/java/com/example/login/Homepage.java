package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Homepage extends AppCompatActivity {

    EditText username, email, password, phonenum;
    Button register;
    ImageView image,imagedummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void process(View view) {
        username = findViewById(R.id.usname);
        email = findViewById(R.id.uemail);
        password = findViewById(R.id.upassword);
        //conformpwd = findViewById(R.id.ucnfpwd);
        register = findViewById(R.id.btn_Register);
        phonenum = findViewById(R.id.phonenum);
        image=findViewById(R.id.imagedummy);

        String susername = username.getText().toString().trim();
        String semail = email.getText().toString().trim();
        String spassword = password.getText().toString().trim();
       // String sconformpwd = conformpwd.getText().toString().trim();
        String sphonenum = phonenum.getText().toString().trim();

        DataHolder obj=new DataHolder(susername,spassword,sphonenum);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("https://console.firebase.google.com/project/registeruser-42647/database/registeruser-42647-default-rtdb/data/~2Fcustomers");
        node.child(semail).setValue(obj);

        username.setText("");
        email.setText("");
        password.setText("");
        //conformpwd.setText("");
        phonenum.setText("");
        Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_LONG).show();
    }
}