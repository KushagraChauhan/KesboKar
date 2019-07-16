package com.kesbokar.kesbokar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.app.Activity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapterMarket extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mActivity;
    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private ArrayList<MarketIem> marketItems;
    public DataAdapterMarket(MarketListing activity ,ArrayList<MarketIem> marketItems) {
        this.mActivity = activity;
        this.marketItems = marketItems;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_market_listing, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MyViewHolder) {

            populateItemRows((MyViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }



    @Override
    public int getItemViewType(int position) {
        return marketItems.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        LinearLayout parent;
        private TextView mln,mlt,mld,price,heading_text;
        ImageView mli;
        private Button blrq,blw;
        public MyViewHolder(@NonNull View view) {
            super(view);
            mli=view.findViewById(R.id.mli);
            mln=view.findViewById(R.id.mln);
            progressBar=view.findViewById(R.id.progressBar);
            price=view.findViewById(R.id.price);
            parent = view.findViewById(R.id.parent);
            //url1=view.findViewById(R.id.url);
//            bld=view.findViewById(R.id.bld);
            mlt=view.findViewById(R.id.mlt);
            mld=view.findViewById(R.id.mld);
            heading_text=view.findViewById(R.id.heading);
//            blrq=view.findViewById(R.id.blrq);
//            blw=view.findViewById(R.id.blw);
        }
    }
    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }






    @Override
    public int getItemCount() {
        return marketItems == null ? 0 : marketItems.size();
    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }
    private void populateItemRows(MyViewHolder holder, int position) {
        MarketIem current=marketItems.get(position);
        String image="https://www.kesbokar.com.au/uploads/product/thumbs/"+current.getImg();
        String bName=current.getBusi_name();
        String bSynop=current.getBusi_synop();
        String price_ad=current.getprice();
        final String city=current.getCity();
        final String url=current.getUrl();
        final int id=current.getId();
        String heading=current.getHeading();
        holder.mln.setText(bName);
        holder.mld.setText(bSynop);
        String ratings=current.getTitle();
        holder.mlt.setText(ratings);
        holder.price.setText("$ "+price_ad);
        holder.heading_text.setText(heading);
        final String title=ratings.toLowerCase().replace(" ","-");
        if(current.getImg()!="null") {
            Picasso.with(mActivity).load(image).fit().centerInside().into(holder.mli);
        }
        else {
            holder.mli.setImageResource(R.drawable.def);
        }
        holder.mli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalUrl="https://www.kesbokar.com.au/marketplace/"+city+"/"+title+"/"+url+"/"+id;
                SharedPreferences get_product_detail= mActivity.getSharedPreferences("entry",0);
                SharedPreferences.Editor editor=get_product_detail.edit();
                editor.putString("entry_level","0");
                editor.apply();
                Intent intent = new Intent(mActivity, WebViewActivity.class);
                intent.putExtra("URL", finalUrl);
                intent.putExtra("url_name",url);
                intent.putExtra("PID",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                mActivity.startActivityForResult(intent,0);
                mActivity.overridePendingTransition(0,0);
                mActivity.finish();

            }
        });
        holder.mln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalUrl="https://www.kesbokar.com.au/marketplace/"+city+"/"+title+"/"+url+"/"+id;
                SharedPreferences get_product_detail= mActivity.getSharedPreferences("entry",0);
                SharedPreferences.Editor editor=get_product_detail.edit();
                editor.putString("entry_level","0");
                editor.apply();
                Intent intent = new Intent(mActivity, WebViewActivity.class);
                intent.putExtra("URL", finalUrl);
                intent.putExtra("url_name",url);
                intent.putExtra("PID",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                mActivity.startActivityForResult(intent,0);
                mActivity.overridePendingTransition(0,0);
                mActivity.finish();
            }
        });
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalUrl="https://www.kesbokar.com.au/marketplace/"+city+"/"+title+"/"+url+"/"+id;
                SharedPreferences get_product_detail= mActivity.getSharedPreferences("entry",0);
                SharedPreferences.Editor editor=get_product_detail.edit();
                editor.putString("entry_level","0");
                editor.apply();
                Intent intent = new Intent(mActivity, WebViewActivity.class);
                intent.putExtra("URL", finalUrl);
                intent.putExtra("url_name",url);
                intent.putExtra("PID",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                mActivity.startActivityForResult(intent,0);
                mActivity.overridePendingTransition(0,0);
                mActivity.finish();
            }
        });
    }


}



