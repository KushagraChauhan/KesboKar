package com.example.kesbokar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterhelpDesk extends BaseAdapter {
    ArrayList<GetHelpDesk> getHelpDesks;
    TextView date,subject,reply,sno;
    LayoutInflater layoutInflater;
    Context context;
    public AdapterhelpDesk(Context context,ArrayList<GetHelpDesk> getHelpDesks)
    {
        this.context=context;
        this.getHelpDesks=getHelpDesks;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return getHelpDesks.size();
    }

    @Override
    public Object getItem(int position) {
        return getHelpDesks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String i=""+position;
        convertView=layoutInflater.inflate(R.layout.adapter_manage_help_desk,null);
        date=convertView.findViewById(R.id.date);
        sno=convertView.findViewById(R.id.Sno);
        subject=convertView.findViewById(R.id.Subject);
        reply=convertView.findViewById(R.id.reply);
        date.setText(getHelpDesks.get(position).getDate());
        subject.setText(getHelpDesks.get(position).getSubject());
        reply.setText(getHelpDesks.get(position).getReply());
        sno.setText(i+1);
        return convertView;
    }
}
