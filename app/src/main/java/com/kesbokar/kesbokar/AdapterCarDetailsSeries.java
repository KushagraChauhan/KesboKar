package com.kesbokar.kesbokar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterCarDetailsSeries extends BaseAdapter{

    private Context context;
    private ArrayList<CarDetailsSeries> CarDetailsSeriesModelArrayList;
    LayoutInflater layoutInflater;
    Activity activity;
    int id;
    String id_series;
    TextView rbName,tvName,tvDesBody,tvDesEngine;

    public AdapterCarDetailsSeries(Context context, Activity activity, ArrayList<CarDetailsSeries> CarDetailsSeriesModelArrayList) {
        this.context = context;
        this.activity=activity;
        this.CarDetailsSeriesModelArrayList = CarDetailsSeriesModelArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return CarDetailsSeriesModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return CarDetailsSeriesModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.listview_series_cardetails,null);
        rbName=convertView.findViewById(R.id.rbName);
        tvName=convertView.findViewById(R.id.tvName);
        tvDesBody=convertView.findViewById(R.id.tvDesBody);
        tvDesEngine=convertView.findViewById(R.id.tvDesEngine);
        tvName.setText(CarDetailsSeriesModelArrayList.get(position).getName());
        tvDesBody.setText(CarDetailsSeriesModelArrayList.get(position).getDes_body());
        tvDesEngine.setText(CarDetailsSeriesModelArrayList.get(position).getDes_engine());
        rbName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=CarDetailsSeriesModelArrayList.get(position).getId();
                id_series=""+id;
                Toast.makeText(context, ""+id_series, Toast.LENGTH_SHORT).show();
                SharedPreferences get= activity.getSharedPreferences("data1",0);
                SharedPreferences.Editor editor=get.edit();
                editor.putString("series",id_series);
                editor.apply();
                rbName.setEnabled(false);
            }
        });

        return convertView;

    }



}
