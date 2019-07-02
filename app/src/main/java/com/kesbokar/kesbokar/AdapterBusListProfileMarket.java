package com.kesbokar.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBusListProfileMarket extends BaseAdapter {
    ArrayList<MarketProfileList> marketProfileLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtStatus;
    Context context;
    public AdapterBusListProfileMarket(Context context, ArrayList<MarketProfileList> marketProfileLists){
        this.context = context;
        this.marketProfileLists = marketProfileLists;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return marketProfileLists.size();
    }

    @Override
    public Object getItem(int i) {
        return marketProfileLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_layout_market,null);
        txtSno = view.findViewById(R.id.adapMTxtSno);
        txtTitle = view.findViewById(R.id.adapMTxtTitle);
        txtStatus = view.findViewById(R.id.adapMTxtStatus);


        txtSno.setText(marketProfileLists.get(i).getTxtSno());
        txtTitle.setText(marketProfileLists.get(i).getTxtTitle());
        int status = marketProfileLists.get(i).getTxtStatus();
        if(status == 0){
            txtStatus.setText("Deactive");
        }else if(status == 1){
            txtStatus.setText("Awaiting Approval");
        }else if(status == 2){
            txtStatus.setText("Active");
        }
        return view;
    }



}
