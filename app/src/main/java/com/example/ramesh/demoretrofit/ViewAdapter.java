package com.example.ramesh.demoretrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ramesh.demoretrofit.ApiResponse.ViewResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ItemViewHolder> {
    Context context;
    ArrayList<ViewResponse.List1> viewResponses;
//    public static final String MyPreference="mypref";
//    public static final String id = "idkey";
//    public static final String name = "namekey";
//    public static final String lname = "lnamekey";
//    public static final String email = "emailkey";
//    public static final String dob = "dobkey";
//    public static final String gender = "genderkey";
//    public static final String password = "passkey";
//    public static final String contect = "contectkey";

   // SharedPreferences sharedPreferences;

    public ViewAdapter(Context context, ArrayList<ViewResponse.List1> viewResponses) {
        this.context = context;
        this.viewResponses = viewResponses;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recylcedesign,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.id.setText(viewResponses.get(i).getId());
        itemViewHolder.name.setText(viewResponses.get(i).getName());
        itemViewHolder.lname.setText(viewResponses.get(i).getLastName());
        itemViewHolder.email.setText(viewResponses.get(i).getEmail());
        itemViewHolder.dob.setText(viewResponses.get(i).getDob());
        itemViewHolder.gender.setText(viewResponses.get(i).getGender());
        itemViewHolder.password.setText(viewResponses.get(i).getPassword());
        itemViewHolder.contect.setText(viewResponses.get(i).getContect());
       // Picasso.get().load(viewResponses.get(i).getPhoto()).into(itemViewHolder.imageView);


       // Glide.with(context).load(viewResponses.get(i).getPhoto()).thumbnail(0.5f).crossFade().into(itemViewHolder.ImagesPro);
        Glide.with(context).load(viewResponses.get(i).getPhoto()).placeholder(R.drawable.ic_assistant_photo_black_24dp).into(itemViewHolder.ImagesPro);


        itemViewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,UpdateActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewResponses.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,lname,email,dob,gender,password,contect;
        Button btn;
        ImageView ImagesPro;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ImagesPro=itemView.findViewById(R.id.ImageviewPro);
            id=itemView.findViewById(R.id.TxtId);
            name=itemView.findViewById(R.id.TxtName);
            lname=itemView.findViewById(R.id.TxtLName);
            email=itemView.findViewById(R.id.TxtEmail);
            dob=itemView.findViewById(R.id.TxtDob);
            gender=itemView.findViewById(R.id.TxtGender);
            password=itemView.findViewById(R.id.TxtPassword);
            contect=itemView.findViewById(R.id.TxtContect);
            btn=itemView.findViewById(R.id.ubtn);

          // SharedPreferences sharedPreferences=contect.getSharedPreferences()
        }
    }
}

