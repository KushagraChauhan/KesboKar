package com.kesbokar.kesbokar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterInboxBusiness extends BaseAdapter {
    private final ArrayList<InboxBusinessList> inboxBusinessLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtAbn,txtPhone,txtStatus;
    Button adpBtnEdt,adptBtnDel;
    Context context;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    int pos;
    Activity activity;
    String id1;
    public AdapterInboxBusiness(Context context, ArrayList<InboxBusinessList> inboxBusinessLists,Activity activity){
        this.context = context;
        this.inboxBusinessLists = inboxBusinessLists;
        this.activity=activity;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.adapter_inbox_business,null);
        txtSno = view.findViewById(R.id.adapTxtSno);
        txtTitle = view.findViewById(R.id.adapTxtTitle);
        txtAbn = view.findViewById(R.id.adapTxtABN);
        txtPhone = view.findViewById(R.id.adapTxtPhone);
        txtStatus = view.findViewById(R.id.adapTxtStatus);
        adpBtnEdt=view.findViewById(R.id.adapBtnEdt);
        adptBtnDel=view.findViewById(R.id.adapBtnDel);
        adpBtnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int en_id=inboxBusinessLists.get(i).getId();
                Intent intent=new Intent(context,InboxReplyMarketplace.class);
                intent.putExtra("en_id",en_id);
                activity.startActivity(intent);
            }
        });
        adptBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue= Volley.newRequestQueue(context);
                String url;

                url="http://serv.kesbokar.com.au/jil.0.1/v1/quotes-yellowpage/delete";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response",response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error",error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        id1=""+inboxBusinessLists.get(i).getId();
                        getData();
                        Map<String, String>  params = new HashMap<String, String >();
                        params.put("id",id1);
                        String user_id=""+id;
                        params.put("user_id",user_id);
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        return params;
                    }
                };
                queue.add(stringRequest);
                Intent intent=new Intent(context,inbox_business.class);
                activity.startActivityForResult(intent, 0);
                activity.overridePendingTransition(0, 0);
                activity.finish();

            }
        });
        txtSno.setText(inboxBusinessLists.get(i).getTxtSno());
        txtTitle.setText(inboxBusinessLists.get(i).getTxtName());
        txtPhone.setText(inboxBusinessLists.get(i).getTxtMessage());
        txtAbn.setText(inboxBusinessLists.get(i).getTxtBusiness());
        txtStatus.setText(inboxBusinessLists.get(i).getTxtDate());
        return view;
    }
    public void getData()
    {
        SharedPreferences loginData=context.getSharedPreferences("data",0);
        flag = loginData.getInt("Flag",0);
        full_name=loginData.getString("Name","");
        email=loginData.getString("mail","");
        image=loginData.getString("image","");
        phone_no=loginData.getString("phone","");
        id=loginData.getInt("id",0);
        created=loginData.getString("create","");
        updated=loginData.getString("update","");

    }
}
