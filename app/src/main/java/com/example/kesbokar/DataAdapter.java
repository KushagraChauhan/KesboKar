package com.example.kesbokar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.app.Activity;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mActivity;
    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ArrayList<ExampleItem> exampleItems;
    private int isLoggedIn;
    public DataAdapter(Buisness_Listing activity ,ArrayList<ExampleItem> exampleList, int flag) {
        this.mActivity = activity;
        exampleItems = exampleList;
        isLoggedIn = flag;
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

            //Request A Quote
            ((MyViewHolder) viewHolder).blrq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String[] preference = new String[1];
                    final Dialog dialog = new Dialog(mActivity);
                    dialog.setContentView(R.layout.request_quote);
                    dialog.setTitle("Request A Quote");
                    dialog.setCancelable(true); //none-dismiss when touching outside Dialog
                    EditText name = (EditText) dialog.findViewById(R.id.etApiName);
                    EditText email = (EditText) dialog.findViewById(R.id.etEmail);
                    EditText phone = (EditText) dialog.findViewById(R.id.etPhone);
                    EditText details = (EditText) dialog.findViewById(R.id.etDetails);
                    TextView LoggedInName =(TextView) dialog.findViewById(R.id.etName);
                    TextView FromName = (TextView) dialog.findViewById(R.id.etFromName);
                    final String[] method = {"No Preference", "Email", "Mobile"};

                    final Button[] btn = {dialog.findViewById(R.id.btnOpenDialog)};
                    btn[0].setClickable(true);
                    btn[0].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                            builder.setTitle("Select Option");
                            builder.setItems(method, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int item) {
                                    preference[0] = method[item];
                                    btn[0].setText(preference[0]);
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }


                    });



                    View btnSubmit = dialog.findViewById(R.id.btnSubmit);
                    View btnClose = dialog.findViewById(R.id.btnClose);
                    if (isLoggedIn == 0) {
                        LoggedInName.setVisibility(View.GONE);
                        FromName.setVisibility(View.GONE);
                    }
                    else {
                        name.setVisibility(View.GONE);
                        email.setVisibility(View.GONE);
                        phone.setVisibility(View.GONE);
                    }


                    btnSubmit.setOnClickListener(onConfirmListener(name, email, phone, details, preference, dialog));
                    btnClose.setOnClickListener(onCancelListener(dialog));
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                }
            });



        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }


    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }



    private View.OnClickListener onConfirmListener(EditText name, EditText email, EditText phone, EditText details, String[] preference, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                dialog.dismiss();
            }
        };
        
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
        private Button blw;
        Button blrq;
        public MyViewHolder(@NonNull View view) {
            super(view);
            bln=view.findViewById(R.id.bln);
            bls=view.findViewById(R.id.bls);
            progressBar=view.findViewById(R.id.progressBar);
            //url1=view.findViewById(R.id.url);
//            bld=view.findViewById(R.id.bld);
            bli=view.findViewById(R.id.bli);
            blr=view.findViewById(R.id.blr);
            blrq=view.findViewById(R.id.blrq);
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



