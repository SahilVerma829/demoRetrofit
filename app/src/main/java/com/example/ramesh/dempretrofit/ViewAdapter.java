package com.example.ramesh.dempretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramesh.dempretrofit.ApiResponse.ViewResponse;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ItemViewHolder> {
    Context context;
    ArrayList<ViewResponse.List1> viewResponses;

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
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.TxtId);
            name=itemView.findViewById(R.id.TxtName);
            lname=itemView.findViewById(R.id.TxtLName);
            email=itemView.findViewById(R.id.TxtEmail);
            dob=itemView.findViewById(R.id.TxtDob);
            gender=itemView.findViewById(R.id.TxtGender);
            password=itemView.findViewById(R.id.TxtPassword);
            contect=itemView.findViewById(R.id.TxtContect);
            btn=itemView.findViewById(R.id.ubtn);
        }
    }
}
