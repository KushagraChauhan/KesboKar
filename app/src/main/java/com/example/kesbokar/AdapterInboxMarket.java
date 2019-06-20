package com.example.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterInboxMarket extends BaseAdapter {
    private final ArrayList<InboxMarketList> inboxMarketLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtAbn,txtPhone,txtStatus;
    Context context;
    public AdapterInboxMarket(Context context, ArrayList<InboxMarketList> inboxMarketLists){
        this.context = context;
        this.inboxMarketLists = inboxMarketLists;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return inboxMarketLists.size();
    }

    @Override
    public Object getItem(int i) {
        return inboxMarketLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_inbox_market,null);
        txtSno = view.findViewById(R.id.adapTxtSno);
        txtTitle = view.findViewById(R.id.adapTxtTitle);
        txtAbn = view.findViewById(R.id.adapTxtABN);
        txtPhone = view.findViewById(R.id.adapTxtPhone);
        txtStatus = view.findViewById(R.id.adapTxtStatus);

        txtSno.setText(inboxMarketLists.get(i).getTxtSno());
        txtTitle.setText(inboxMarketLists.get(i).getTxtName());
        txtPhone.setText(inboxMarketLists.get(i).getTxtMessage());
        txtAbn.setText(inboxMarketLists.get(i).getTxtProduct());
        int status = inboxMarketLists.get(i).getTxtDate();
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
