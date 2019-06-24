package com.example.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBusListProfile extends BaseAdapter {
    ArrayList<BusinessProfileList> businessProfileLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtAbn,txtPhone,txtStatus;
    Context context;
    public AdapterBusListProfile(Context context, ArrayList<BusinessProfileList> businessProfileLists){
        this.context = context;
        this.businessProfileLists = businessProfileLists;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return businessProfileLists.size();
    }

    @Override
    public Object getItem(int i) {
        return businessProfileLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_layout,null);
        txtSno = view.findViewById(R.id.adapTxtSno);
        txtTitle = view.findViewById(R.id.adapTxtTitle);
        txtAbn = view.findViewById(R.id.adapTxtABN);
        txtPhone = view.findViewById(R.id.adapTxtPhone);
        txtStatus = view.findViewById(R.id.adapTxtStatus);
        txtSno.setText(businessProfileLists.get(i).getTxtSno());
        txtTitle.setText(businessProfileLists.get(i).getTxtTitle());
        txtPhone.setText(businessProfileLists.get(i).getTxtPhone());
        txtAbn.setText(businessProfileLists.get(i).getTxtAbn());
        int status = businessProfileLists.get(i).getTxtStatus();
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
