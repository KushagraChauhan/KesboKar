package com.example.acer.pocjil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class BusSrchAdapter extends BaseAdapter implements Filterable {
    ArrayList<BusinessSearchBar> businessSearchBars = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    TextView mtext;

    public BusSrchAdapter(ArrayList<BusinessSearchBar> businessSearchBars, Context context){
        this.businessSearchBars = businessSearchBars;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return businessSearchBars.size();
    }

    @Override
    public Object getItem(int position) {
        return businessSearchBars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.autocomp_bussrch_adapter,null);
        mtext = view.findViewById(R.id.simple_text);
        mtext.setText(businessSearchBars.get(position).getValue());
        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
