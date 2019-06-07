package com.example.kesbokar;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.app.Activity;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    Activity mActivity;
    public DataAdapter(Buisness_Listing activity)
    {
        this.mActivity=activity;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView bln,bls,bld;
        RatingBar blr;
        ImageView bli;
        private Button blrq,blw;
        public MyViewHolder(@NonNull View view) {
            super(view);
            bln=view.findViewById(R.id.bln);
            bls=view.findViewById(R.id.bls);
            //url1=view.findViewById(R.id.url);
            bld=view.findViewById(R.id.bld);
            bli=view.findViewById(R.id.bli);
            blr=view.findViewById(R.id.blr);
            blrq=view.findViewById(R.id.blrq);
            blw=view.findViewById(R.id.blw);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_business_listing,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

    }

    @Override
    public int getItemCount() {
        return getItemCount();
    }


}



