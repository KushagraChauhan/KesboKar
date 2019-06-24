package com.example.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterInboxBusiness extends BaseAdapter {
    private final ArrayList<InboxBusinessList> inboxBusinessLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtAbn,txtPhone,txtStatus;
    Context context;
    public AdapterInboxBusiness(Context context, ArrayList<InboxBusinessList> inboxBusinessLists){
        this.context = context;
        this.inboxBusinessLists = inboxBusinessLists;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return inboxBusinessLists.size();
    }

    @Override
    public Object getItem(int i) {
        return inboxBusinessLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_inbox_business,null);
        txtSno = view.findViewById(R.id.adapTxtSno);
        txtTitle = view.findViewById(R.id.adapTxtTitle);
        txtAbn = view.findViewById(R.id.adapTxtABN);
        txtPhone = view.findViewById(R.id.adapTxtPhone);
        txtStatus = view.findViewById(R.id.adapTxtStatus);

        txtSno.setText(inboxBusinessLists.get(i).getTxtSno());
        txtTitle.setText(inboxBusinessLists.get(i).getTxtName());
        txtPhone.setText(inboxBusinessLists.get(i).getTxtMessage());
        txtAbn.setText(inboxBusinessLists.get(i).getTxtBusiness());
        txtStatus.setText(inboxBusinessLists.get(i).getTxtDate());
        return view;
    }
}
