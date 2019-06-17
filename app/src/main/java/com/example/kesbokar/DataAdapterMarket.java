package com.example.kesbokar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
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
        private TextView mln,mlt,mld;
        ImageView mli;
        private Button blrq,blw;
        public MyViewHolder(@NonNull View view) {
            super(view);
            mli=view.findViewById(R.id.mli);
            mln=view.findViewById(R.id.mln);
            progressBar=view.findViewById(R.id.progressBar);
            //url1=view.findViewById(R.id.url);
//            bld=view.findViewById(R.id.bld);
            mlt=view.findViewById(R.id.mlt);
            mld=view.findViewById(R.id.mld);
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
        final String city=current.getCity();
        final String url=current.getUrl();
        final int id=current.getId();
        holder.mln.setText(bName);
        holder.mld.setText(bSynop);
        String ratings=current.getTitle();
        holder.mlt.setText(ratings);
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
                Intent intent = new Intent(mActivity, WebViewActivity.class);
                intent.putExtra("URL", finalUrl);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                mActivity.startActivityForResult(intent,0);
                mActivity.overridePendingTransition(0,0);
                mActivity.finish();

            }
        });

    }


}



