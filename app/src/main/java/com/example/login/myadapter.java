package com.example.login;

import static android.app.PendingIntent.getActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    ImageView img1;
    String id;

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {


        //holder.txtid.setText(model.getId());

        holder.loctxt.setText(model.getLocation());
        holder.mailtxt.setText(model.getEmail());
        holder.mobileNotxt.setText(model.getMobile_No());
        holder.nametxt.setText(model.getName());
        holder.pg_nametxt.setText(model.getPg_Name());
        holder.pricetxt.setText(model.getPg_Price());


        FirebaseAuth fauth = FirebaseAuth.getInstance();
        String userId = fauth.getUid();

        try {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference dateRef = storageRef.child("PG/"+model.getId());
            dateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
            {
                @Override
                public void onSuccess(Uri url)
                {
                    Glide.with(holder.img1.getContext()).load(url).into(holder.img1);
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
            //Toast.makeText(getApplicationContext(),"Error"+e.toString(),Toast.LENGTH_LONG).show();
        }


        //String url = "gs://registeruser-42647.appspot.com/PG/" + id;
        //Glide.with(holder.img1.getContext()).load(url).into(holder.img1);
        //retrieve image

         /*StorageReference storageReference = FirebaseStorage.getInstance().getReference("PG/"+id);
        try {
            File loc= File.createTempFile("tempfile",",jpeg");
            storageReference.getFile(loc)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(loc.getAbsolutePath());
                            img1.setImageBitmap(bitmap);
                           // Toast.makeText(myadapter.this, "error: "+loc, Toast.LENGTH_SHORT).show();
                            System.out.println("error");
                            //obj.image.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                           // Toast.makeText(,"Error"+e.toString(),Toast.LENGTH_LONG).show();
                           // Toast.makeText(getActivity, "", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        holder.txt_viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new descfragment(model.getAttached_Washroom(), model.getElectricity_Bill(), model.getEmail(), model.getId(), model.getLocation(), model.getMess_Facility(), model.getMobile_No(), model.getName(), model.getPg_Name(), model.pg_Price, model.getPg_Type(), model.getProfession(), model.getRack_No(), model.getSeperate_Washroom())).addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerowdesign, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView txtid, loctxt, mailtxt, mobileNotxt, nametxt, pg_nametxt, professiontxt;
        TextView txt_viewmore, pricetxt;
        String id;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            txtid = itemView.findViewById(R.id.txtid);
            loctxt = itemView.findViewById(R.id.loctxt);

            mailtxt = itemView.findViewById(R.id.mailtxt);
            mobileNotxt = itemView.findViewById(R.id.mobileNotxt);
            nametxt = itemView.findViewById(R.id.nametxt);
            pg_nametxt = itemView.findViewById(R.id.pg_nametxt);
            // professiontxt=itemView.findViewById(R.id.professiontxt);
            txt_viewmore = itemView.findViewById(R.id.txt_viewmore);
            pricetxt = itemView.findViewById(R.id.pricetxt);
            // String id = model.getId();
            id = (String) txtid.getText();

//retriving images


        }
    }

}
