package com.example.login;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class descfragment extends Fragment {



    FirebaseAuth fauth ;
    Button b1;
    DatabaseReference reference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String attached_Washroom, electricity_Bill, id, email, location, mess_Facility, mobile_No, name, pg_Name, pg_Price, pg_Type, profession, rack_No, seperate_Washroom;


    public descfragment() {
        // Required empty public constructor
    }


    public descfragment(String attached_Washroom, String electricity_Bill, String email, String id, String location, String mess_Facility, String mobile_No, String name, String pg_Name, String pg_Price, String pg_Type, String profession, String rack_No, String seperate_Washroom) {

        this.attached_Washroom = attached_Washroom;
        this.electricity_Bill = electricity_Bill;
        this.email = email;
        this.id = id;
        this.location = location;
        this.mess_Facility = mess_Facility;
        this.mobile_No = mobile_No;
        this.name = name;
        this.pg_Name = pg_Name;
        this.pg_Type = pg_Type;
        this.profession = profession;
        this.rack_No = rack_No;
        this.seperate_Washroom = seperate_Washroom;
        this.pg_Price = pg_Price;
    }


    public static descfragment newInstance(String param1, String param2) {
        descfragment fragment = new descfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_descfragment, container, false);
        ImageView imgholder = view.findViewById(R.id.img1);
        TextView idholder =view.findViewById(R.id.idholder);
        TextView locationholder = view.findViewById(R.id.locationholder);
        TextView nameholder = view.findViewById(R.id.nameholder);
        TextView pg_Nameholder = view.findViewById(R.id.pg_Nameholder);
        TextView emailholder = view.findViewById(R.id.emailholder);
        TextView pg_Typeholder = view.findViewById(R.id.pg_Typeholder);
        TextView mobile_Noholder = view.findViewById(R.id.mobile_Noholder);
        TextView professionholder = view.findViewById(R.id.professionholder);
        TextView attacher_Washroomholder = view.findViewById(R.id.attached_Washroomholder);
        TextView electricity_Billholder = view.findViewById(R.id.electricity_Billholder);
        TextView rack_Noholder = view.findViewById(R.id.rack_Noholder);
        TextView seperate_Washroomholder = view.findViewById(R.id.seprate_Washroomholder);
        TextView mess_Facilityholder = view.findViewById(R.id.mess_Facilityholder);
        TextView priceholder = view.findViewById(R.id.priceholder);



        //retrieve image

           /* StorageReference storageReference = FirebaseStorage.getInstance().getReference("PG/"+id);
            try {
                File loc= File.createTempFile("tempfile",",jpeg");
                storageReference.getFile(loc)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap= BitmapFactory.decodeFile(loc.getAbsolutePath());
                                img1.setImageBitmap(bitmap);
                                //obj.image.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(),"Error"+e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"Error"+e.toString(),Toast.LENGTH_LONG).show();
            }*/


        b1 = view.findViewById(R.id.book_pg);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobileno = mobile_No.toString();


                //Intent intent = new Intent(getActivity(), msg_send.class);
                //  intent= intent.putExtra("key",mobile_No.toString());
                // startActivity(intent);

                String str = "Sending message as booking PG!!!!";
                //create intent
                try {
                    Intent bookpg = new Intent(getActivity(), descfragment.class);


                    //Pending Intent
                    PendingIntent my_pi = PendingIntent.getActivity(getActivity(), 0, bookpg, 0);
                    SmsManager mysmsmanager = SmsManager.getDefault();
                    mysmsmanager.sendTextMessage(mobileno, null, str, my_pi, null);
                    Toast.makeText(getActivity(), "Successfully msg send" , Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error" + e.toString(), Toast.LENGTH_LONG).show();
                }


            }
        });


        idholder.setText("your id s  "+id);
        locationholder.setText("Location:  " + location);
        nameholder.setText("Name: " + name);
        pg_Nameholder.setText("PG Name:  " + pg_Name);
        emailholder.setText("Email: " + email);
        pg_Typeholder.setText("PG Type: " + pg_Type);
        mobile_Noholder.setText("Mobile No:  " + mobile_No);
        attacher_Washroomholder.setText("Attached Washroom :  " + attached_Washroom);
        electricity_Billholder.setText("Electricity:  " + electricity_Bill);
        rack_Noholder.setText("No. Racks:  " + rack_No);
        seperate_Washroomholder.setText("Seprate Washrooms:  " + seperate_Washroom);
        mess_Facilityholder.setText("Mess Facility:  " + mess_Facility);
        priceholder.setText("Total Price :  " + pg_Price + "/-");


        professionholder.setText("Profession:  " + profession);

        //Glide.with(getContext()).load(purl).into(imgholder);

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
                    Glide.with(getActivity())
                            .load(url)
                            .error(R.drawable.pgroom)
                            .fallback(R.drawable.loginphoto)
                            .into(imgholder);
                    Toast.makeText(getActivity(), "Successfully img loaded"+ url  , Toast.LENGTH_LONG).show();
                    // Toast.makeText(getApplicationContext(),"Successfully uploaded",Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),"Error"+e.toString(),Toast.LENGTH_LONG).show();
        }
               /* }

        try {
           // fauth = FirebaseAuth.getInstance();
            //String userId= (String) idholder.getText();
           *//* Glide.with(getContext())
                    .load("https://t3.ftcdn.net/jpg/03/37/21/84/360_F_337218440_SONJ5Gxvzx0CEADooBWvwInuLrVu7E8Q.jpg")
                    .placeholder(R.drawable.pgroom)
                    .error(R.drawable.splashimg)
                    .fallback(R.drawable.loginphoto)
                    .into(imgholder);
            Toast.makeText(getActivity(), "Successfully img loaded"+id  , Toast.LENGTH_LONG).show();*//*

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error" + e.toString(), Toast.LENGTH_LONG).show();
        }*/
        return view;
    }


    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new recfragment()).addToBackStack(null).commit();

    }


}