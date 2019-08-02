package com.kesbokar.kesbokar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarketPlaceApiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView bc, bd;
    private ImageView bi;

    ArrayList<MarketPlaceApi> marketPlaceApis;
    Context context;
    Activity activity;

    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;


    public MarketPlaceApiAdapter(Context context, ArrayList<MarketPlaceApi> marketPlaceApis, Activity activity) {
        this.context=context;
        this.activity=activity;
        this.marketPlaceApis=marketPlaceApis;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_expert_layout, parent, false);



        return new MarketPlaceApiAdapter.MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View view) {
            super(view);
            bc=view.findViewById(R.id.bc1);
            bi=view.findViewById(R.id.bi1);
            bd=view.findViewById(R.id.bd1);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        bc.setText(marketPlaceApis.get(position).getName());
        bd.setText(marketPlaceApis.get(position).getCat_title() + " - " + marketPlaceApis.get(position).getCity().getTitle() + " , " + marketPlaceApis.get(position).getState().getTitle());

        String imgURL = "https://www.kesbokar.com.au/uploads/product/thumbs/" + marketPlaceApis.get(position).getImageLogo();
        Picasso.with(context).load(imgURL).into(bi);

        //new DownLoadImageTask(bi[i]).execute(imgURL);
        final int index = position;
        final String ab = marketPlaceApis.get(position).getCity().getTitle().replaceAll(" ", "+");

        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ab = marketPlaceApis.get(index).getCity().getTitle().replaceAll(" ", "+");
                final String url = "https://www.kesbokar.com.au/marketplace/" + ab + "/" + marketPlaceApis.get(index).getCat_title() + "/" + marketPlaceApis.get(index).getUrlname() + "/" + marketPlaceApis.get(index).getId();
                SharedPreferences get_product_detail= context.getSharedPreferences("entry",0);
                SharedPreferences.Editor editor=get_product_detail.edit();
                editor.putString("entry_level","1");
                editor.apply();
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("URL", url);
                intent.putExtra("Flag", flag);
                intent.putExtra("Name",full_name);
                intent.putExtra("mail",email);
                intent.putExtra("image",image);
                intent.putExtra("phone",phone_no);
                intent.putExtra("create",created);
                intent.putExtra("update",updated);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                activity.startActivityForResult(intent, 0);
                activity.overridePendingTransition(0, 0);


            }
        });
    }

    @Override
    public int getItemCount() {
        return marketPlaceApis.size();
    }
    public void getData()
    {
        SharedPreferences loginData=context.getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");
    }
}

