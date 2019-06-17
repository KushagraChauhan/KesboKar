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

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mActivity;
    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<ExampleItem> exampleItems;
    public DataAdapter(Buisness_Listing activity ,ArrayList<ExampleItem> exampleList) {
        this.mActivity = activity;
        exampleItems = exampleList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_business_listing, parent, false);
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
        return exampleItems.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        private TextView bln,bls,bld;
        RatingBar blr;
        ImageView bli;
        private Button blrq,blw;
        public MyViewHolder(@NonNull View view) {
            super(view);
            bln=view.findViewById(R.id.bln);
            bls=view.findViewById(R.id.bls);
            progressBar=view.findViewById(R.id.progressBar);
            //url1=view.findViewById(R.id.url);
//            bld=view.findViewById(R.id.bld);
            bli=view.findViewById(R.id.bli);
            blr=view.findViewById(R.id.blr);
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
        return exampleItems == null ? 0 : exampleItems.size();
    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }
    private void populateItemRows(MyViewHolder holder, int position) {
        ExampleItem current=exampleItems.get(position);
        String image="https://www.kesbokar.com.au/uploads/yellowpage/"+current.getImg();
        String bName=current.getBusi_name();
        String bSynop=current.getBusi_synop();
        final String city=current.getCity();
        final String url=current.getUrl();
        final int id=current.getId();
        holder.bln.setText(bName);
        holder.bls.setText(bSynop);
        float ratings=(float)current.getratings();
        holder.blr.setRating(ratings);
        if(current.getImg()==null)
        {
            holder.bli.setImageResource(R.drawable.def);
        }
        else if(current.getImg()!="null") {
            Picasso.with(mActivity).load(image).fit().centerInside().into(holder.bli);
        }
        else {
            holder.bli.setImageResource(R.drawable.def);
        }
        holder.bli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalUrl="https://www.kesbokar.com.au/business/"+city+"/"+url+"/"+id;
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



