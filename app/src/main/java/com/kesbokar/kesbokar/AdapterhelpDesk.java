package com.kesbokar.kesbokar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterhelpDesk extends BaseAdapter {
    ArrayList<GetHelpDesk> getHelpDesks;
    TextView date,subject,reply,sno;
    LayoutInflater layoutInflater;
    Button reply_page;
    Context context;
    Activity activity;
    public AdapterhelpDesk(Context context ,ManageHelpDeskActivity activity ,ArrayList<GetHelpDesk> getHelpDesks)
    {
        this.context=context;
        this.activity=activity;
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
        int pos=position+1;
        String i=""+pos;
        convertView=layoutInflater.inflate(R.layout.adapter_manage_help_desk,null);
        date=convertView.findViewById(R.id.date);
        sno=convertView.findViewById(R.id.Sno);
        reply_page=convertView.findViewById(R.id.information);
        subject=convertView.findViewById(R.id.Subject);
        reply=convertView.findViewById(R.id.reply);
        date.setText(getHelpDesks.get(position).getDate());
        subject.setText(getHelpDesks.get(position).getSubject());
        final int id1=getHelpDesks.get(position).getId1();
        reply.setText(getHelpDesks.get(position).getReply());
        sno.setText(i);
        reply_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Reply_Help_Desk.class);
                intent.putExtra("id",id1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                activity.startActivityForResult(intent, 0);
                activity.overridePendingTransition(0, 0);
            }
        });
        return convertView;
    }
}
