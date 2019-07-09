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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterBusListProfile extends BaseAdapter {
    ArrayList<BusinessProfileList> businessProfileLists;
    LayoutInflater layoutInflater;
    TextView txtSno,txtTitle,txtAbn,txtPhone,txtStatus;
    Button adpBtnDel;
    Context context;
    String loginId, loginPass, full_name, email, image, phone_no,created,updated;
    int id,flag;
    int pos;
    Activity activity;
    String id1;
    public AdapterBusListProfile(Context context, ArrayList<BusinessProfileList> businessProfileLists, Activity activity){
        this.context = context;
        this.activity=activity;
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
        adpBtnDel=view.findViewById(R.id.adapBtnDel);
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
        adpBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue= Volley.newRequestQueue(context);
                //Toast.makeText(Help.this, "Ipaddress"+ip, Toast.LENGTH_SHORT).show();

                String url="http://serv.kesbokar.com.au/jil.0.1/v1/yellowpage/delete";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Response"+"Your Query Has been Submitted", Toast.LENGTH_SHORT).show();
                        Log.i("Resposnse",response);

                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // errorLog.d("Error.Response", String.valueOf(error));
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        id1=""+businessProfileLists.get(pos).getId();
                        getData();
                        String user_id=""+id;
                        Map<String, String>  params = new HashMap<String, String >();
                        params.put("id",id1);
                        params.put("api_token","FSMNrrMCrXp2zbym9cun7phBi3n2gs924aYCMDEkFoz17XovFHhIcZZfCCdK");
                        params.put("user_id",user_id);
                        return params;
                    }
                };
                queue.add(stringRequest);
                Intent intent=new Intent(context,ProfileBusinessListing.class);
                activity.startActivityForResult(intent, 0);
                activity.overridePendingTransition(0, 0);
                activity.finish();

            }
        });
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
